package com.macro.mall.service.impl;

import com.macro.mall.common.exception.ApiException;
import com.macro.mall.dao.SmsGroupBuyActivityDao;
import com.macro.mall.dto.SmsGroupBuyProductParam;
import com.macro.mall.mapper.SmsGroupBuyProductMapper;
import com.macro.mall.model.SmsGroupBuyProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SmsGroupBuyProductServiceImplTest {

    @Mock
    private SmsGroupBuyProductMapper productMapper;

    @Mock
    private SmsGroupBuyActivityDao activityDao;

    @InjectMocks
    private SmsGroupBuyProductServiceImpl service;

    private SmsGroupBuyProduct aProduct(long skuId) {
        SmsGroupBuyProduct p = new SmsGroupBuyProduct();
        p.setProductId(1L);
        p.setProductSkuId(skuId);
        p.setOriginalPrice(new BigDecimal("199.00"));
        p.setGroupPrice(new BigDecimal("99.00"));
        p.setGroupStock(100);
        return p;
    }

    @Test
    void createBatch_shouldFailOnEmptyList() {
        SmsGroupBuyProductParam param = new SmsGroupBuyProductParam();
        param.setActivityId(10L);
        param.setProductList(Collections.emptyList());
        assertThrows(ApiException.class, () -> service.createBatch(param));
    }

    @Test
    void createBatch_shouldFillDefaultsAndPersist() {
        SmsGroupBuyProductParam param = new SmsGroupBuyProductParam();
        param.setActivityId(10L);
        param.setProductList(new ArrayList<>(Arrays.asList(aProduct(1L), aProduct(2L))));
        when(productMapper.insertSelective(any())).thenReturn(1);

        int total = service.createBatch(param);

        assertEquals(2, total);
        ArgumentCaptor<SmsGroupBuyProduct> captor = ArgumentCaptor.forClass(SmsGroupBuyProduct.class);
        verify(productMapper, times(2)).insertSelective(captor.capture());
        for (SmsGroupBuyProduct saved : captor.getAllValues()) {
            assertEquals(10L, saved.getActivityId());
            assertEquals(0, saved.getLockedStock());
            assertEquals(0, saved.getSoldCount());
            assertEquals(1, saved.getLimitPerOrder());
            assertEquals(0, saved.getSort());
        }
    }

    @Test
    void update_shouldStripImmutableFields() {
        SmsGroupBuyProduct in = aProduct(3L);
        in.setActivityId(999L);
        in.setLockedStock(50);
        in.setSoldCount(60);
        when(productMapper.updateByPrimaryKeySelective(any())).thenReturn(1);
        ArgumentCaptor<SmsGroupBuyProduct> captor = ArgumentCaptor.forClass(SmsGroupBuyProduct.class);

        int count = service.update(100L, in);

        assertEquals(1, count);
        verify(productMapper).updateByPrimaryKeySelective(captor.capture());
        SmsGroupBuyProduct persisted = captor.getValue();
        assertEquals(100L, persisted.getId());
        assertNull(persisted.getActivityId());
        assertNull(persisted.getLockedStock());
        assertNull(persisted.getSoldCount());
        assertEquals(new BigDecimal("99.00"), persisted.getGroupPrice());
    }

    @Test
    void delete_shouldRejectWhenLockedStockGreaterThanZero() {
        SmsGroupBuyProduct existing = aProduct(1L);
        existing.setLockedStock(5);
        when(productMapper.selectByPrimaryKey(7L)).thenReturn(existing);

        assertThrows(ApiException.class, () -> service.delete(7L));
        verify(productMapper, never()).deleteByPrimaryKey(any());
    }

    @Test
    void delete_shouldPassWhenNoLockedStock() {
        SmsGroupBuyProduct existing = aProduct(1L);
        existing.setLockedStock(0);
        when(productMapper.selectByPrimaryKey(7L)).thenReturn(existing);
        when(productMapper.deleteByPrimaryKey(7L)).thenReturn(1);

        assertEquals(1, service.delete(7L));
    }

    @Test
    void listByActivity_shouldDelegateToDao() {
        List<SmsGroupBuyProduct> products = Arrays.asList(aProduct(1L), aProduct(2L));
        when(activityDao.listProductByActivity(10L)).thenReturn(products);
        assertEquals(2, service.listByActivity(10L).size());
    }
}
