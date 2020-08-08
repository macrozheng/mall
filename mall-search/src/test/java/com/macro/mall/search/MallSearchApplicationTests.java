package com.macro.mall.search;

import com.macro.mall.search.dao.EsProductDao;
import com.macro.mall.search.domain.EsProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallSearchApplicationTests {
    @Autowired
    private EsProductDao productDao;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testGetAllEsProductList(){
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        System.out.print(esProductList);
    }
    @Test
    public void testEsProductMapping(){
        IndexOperations indexOperations = elasticsearchTemplate.indexOps(EsProduct.class);
        indexOperations.putMapping(indexOperations.createMapping(EsProduct.class));
        Map mapping = indexOperations.getMapping();
        System.out.println(mapping);
    }

}
