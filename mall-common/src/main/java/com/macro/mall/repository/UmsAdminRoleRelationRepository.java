package com.macro.mall.repository;

import com.macro.mall.model.UmsAdminRoleRelation;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsAdminRoleRelationRepository extends JpaRepository<UmsAdminRoleRelation, Long>, JpaSpecificationExecutor<UmsAdminRoleRelation> {
    @Query("SELECT r FROM UmsRole r JOIN UmsAdminRoleRelation ar ON r.id = ar.roleId WHERE ar.adminId = :adminId")
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    @Query("SELECT DISTINCT ur FROM UmsResource ur " +
           "JOIN UmsRoleResourceRelation rrr ON ur.id = rrr.resourceId " +
           "JOIN UmsAdminRoleRelation ar ON rrr.roleId = ar.roleId " +
           "WHERE ar.adminId = :adminId")
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);
}
