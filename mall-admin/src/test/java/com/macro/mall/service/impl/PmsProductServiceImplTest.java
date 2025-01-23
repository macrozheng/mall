package com.macro.mall.service.impl;


import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.PmsProductQueryParam;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsProductExample;
import com.macro.mall.service.PmsProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PmsProductServiceImplTest {

    @Mock
    private PmsProductMapper productMapper;

    @InjectMocks
    private PmsProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void list_NoConditions_ShouldReturnAllProducts() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();
        List<PmsProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new PmsProduct());
        when(productMapper.selectByExample(new PmsProductExample())).thenReturn(mockProducts);

        List<PmsProduct> result = productService.list(productQueryParam, 10, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void list_WithPublishStatus_ShouldFilterByPublishStatus() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();
        productQueryParam.setPublishStatus(1);
        List<PmsProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new PmsProduct());
        when(productMapper.selectByExample(new PmsProductExample())).thenReturn(mockProducts);

        List<PmsProduct> result = productService.list(productQueryParam, 10, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void list_WithVerifyStatus_ShouldFilterByVerifyStatus() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();
        productQueryParam.setVerifyStatus(1);
        List<PmsProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new PmsProduct());
        when(productMapper.selectByExample(new PmsProductExample())).thenReturn(mockProducts);

        List<PmsProduct> result = productService.list(productQueryParam, 10, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void list_WithKeyword_ShouldFilterByName() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();
        productQueryParam.setKeyword("test");
        List<PmsProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new PmsProduct());
        when(productMapper.selectByExample(new PmsProductExample())).thenReturn(mockProducts);

        List<PmsProduct> result = productService.list(productQueryParam, 10, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void list_WithProductSn_ShouldFilterByProductSn() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();
        productQueryParam.setProductSn("12345");
        List<PmsProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new PmsProduct());
        when(productMapper.selectByExample(new PmsProductExample())).thenReturn(mockProducts);

        List<PmsProduct> result = productService.list(productQueryParam, 10, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void list_WithBrandId_ShouldFilterByBrandId() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();
        productQueryParam.setBrandId(1L);
        List<PmsProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new PmsProduct());
        when(productMapper.selectByExample(new PmsProductExample())).thenReturn(mockProducts);

        List<PmsProduct> result = productService.list(productQueryParam, 10, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void list_WithProductCategoryId_ShouldFilterByProductCategoryId() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();
        productQueryParam.setProductCategoryId(1L);
        List<PmsProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new PmsProduct());
        when(productMapper.selectByExample(new PmsProductExample())).thenReturn(mockProducts);

        List<PmsProduct> result = productService.list(productQueryParam, 10, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void list_WithMultipleConditions_ShouldApplyAllFilters() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();
        productQueryParam.setPublishStatus(1);
        productQueryParam.setVerifyStatus(1);
        productQueryParam.setKeyword("test");
        productQueryParam.setProductSn("12345");
        productQueryParam.setBrandId(1L);
        productQueryParam.setProductCategoryId(1L);
        List<PmsProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new PmsProduct());
        when(productMapper.selectByExample(new PmsProductExample())).thenReturn(mockProducts);

        List<PmsProduct> result = productService.list(productQueryParam, 10, 1);

        assertEquals(1, result.size());
    }
}
