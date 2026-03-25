package com.macro.mall.repository;

import com.macro.mall.model.UmsAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UmsAdminRepository extends JpaRepository<UmsAdmin, Long>, JpaSpecificationExecutor<UmsAdmin> {
    Optional<UmsAdmin> findByUsername(String username);
    Optional<UmsAdmin> findByUsernameAndStatus(String username, Integer status);
}
