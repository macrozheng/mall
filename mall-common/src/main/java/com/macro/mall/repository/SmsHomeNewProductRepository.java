package com.macro.mall.repository;

import com.macro.mall.model.SmsHomeNewProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsHomeNewProductRepository extends JpaRepository<SmsHomeNewProduct, Long>, JpaSpecificationExecutor<SmsHomeNewProduct> {
}
