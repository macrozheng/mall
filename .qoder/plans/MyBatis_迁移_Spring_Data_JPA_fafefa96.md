# MyBatis 迁移 Spring Data JPA 计划

## 一、依赖配置修改

### 1.1 父 pom.xml 修改
- 移除：`pagehelper-spring-boot-starter`、`mybatis-spring-boot-starter`、`mybatis-generator-core`
- 添加：`spring-boot-starter-data-jpa`

### 1.2 mall-mbg 模块重构
- 移除 MyBatis Generator 相关代码
- 删除 `CommentGenerator.java`、`Generator.java`
- 删除 `generatorConfig.xml`、`generator.properties`
- 删除所有 `*Mapper.xml` 文件

## 二、实体类改造（76 个）

### 2.1 添加 JPA 注解
```java
@Entity
@Table(name = "ums_admin")
public class UmsAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username")
    private String username;
    // ...
}
```

### 2.2 需要处理的实体
- `Pms*` - 商品相关（19 个）
- `Oms*` - 订单相关（10 个）
- `Ums*` - 用户相关（26 个）
- `Sms*` - 营销相关（14 个）
- `Cms*` - 内容相关（7 个）

## 三、Repository 接口创建（76 个）

### 3.1 基础 Repository 模式
```java
public interface UmsAdminRepository 
    extends JpaRepository<UmsAdmin, Long>, JpaSpecificationExecutor<UmsAdmin> {
    // 自定义查询方法
    Optional<UmsAdmin> findByUsername(String username);
}
```

### 3.2 删除文件
- 所有 `*Mapper.java` 接口
- 所有 `*Example.java` 类

## 四、Specification 工具类

### 4.1 创建通用 Specification 工具类
```java
public class SpecificationUtil {
    public static <T> Specification<T> buildSpecification(
        Consumer<SpecificationBuilder<T>> builderConsumer) {
        // 动态构建查询条件
    }
}
```

### 4.2 提供便捷的链式 API 替代 Example

## 五、Service 层改造

### 5.1 注入方式变更
```java
// Before
@Autowired
private PmsBrandMapper brandMapper;

// After
@Autowired
private PmsBrandRepository brandRepository;
```

### 5.2 查询方式变更
```java
// Before (MyBatis Example)
PmsBrandExample example = new PmsBrandExample();
example.createCriteria().andNameLike("%" + keyword + "%");
brandMapper.selectByExample(example);

// After (JPA Specification)
Specification<PmsBrand> spec = (root, query, cb) -> 
    cb.like(root.get("name"), "%" + keyword + "%");
brandRepository.findAll(spec);
```

### 5.3 分页方式变更
```java
// Before (PageHelper)
PageHelper.startPage(pageNum, pageSize);
List<PmsBrand> list = brandMapper.selectByExample(example);

// After (Spring Data Pageable)
Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
Page<PmsBrand> page = brandRepository.findAll(spec, pageable);
```

## 六、需要修改的文件清单

### 6.1 mall-mbg 模块
| 操作 | 文件/目录 |
|------|----------|
| 删除 | `Generator.java`, `CommentGenerator.java` |
| 删除 | `mapper/*.java` (76 个) |
| 删除 | `model/*Example.java` (76 个) |
| 删除 | `resources/com/macro/mall/mapper/*.xml` (76 个) |
| 删除 | `generatorConfig.xml`, `generator.properties` |
| 修改 | 所有实体类添加 JPA 注解 |

### 6.2 mall-admin 模块
- `pom.xml` - 依赖调整
- 25+ Service 实现类 - Mapper 改 Repository
- 分页工具类 CommonPage - 适配 Spring Data Page

### 6.3 mall-portal 模块
- `pom.xml` - 依赖调整
- Service 实现类 - 同上

### 6.4 mall-search 模块
- `pom.xml` - 依赖调整

### 6.5 mall-common 模块
- `pom.xml` - 依赖调整
- 添加 JPA 相关工具类

## 七、执行顺序

1. **依赖配置** - 修改 pom.xml
2. **实体类** - 添加 JPA 注解
3. **Repository** - 创建接口
4. **工具类** - Specification 工具、分页适配
5. **Service 层** - 逐个改造
6. **清理** - 删除 MyBatis 相关文件
7. **测试验证** - 确保功能正常

## 八、预计工作量

| 任务 | 数量 | 预计时间 |
|------|------|----------|
| 实体类添加注解 | 76 | - |
| Repository 创建 | 76 | - |
| Service 改造 | 30+ | - |
| 删除旧文件 | 230+ | - |

## 九、风险提示

1. **复杂查询**：部分多表关联查询可能需要使用 `@Query` 或原生 SQL
2. **性能差异**：JPA 的 N+1 问题需要注意，可能需要 `@EntityGraph` 或 `JOIN FETCH`
3. **分页差异**：PageHelper 和 Spring Data 分页起始页码不同（1 vs 0）