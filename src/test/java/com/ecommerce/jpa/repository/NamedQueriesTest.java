package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class NamedQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testNamedJPQLQueryByIndexParam() {
        List<Product> products = productRepository.findByPrice(new BigDecimal(100));
        products.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    void namedJPALQueries() {
        List<Product> products = productRepository.findAllOrderByNameDesc();
        products.forEach(p -> System.out.println(p.toString()));

        List<Product> products1 = productRepository.findByPrice(new BigDecimal(100));
        products1.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    void testNamedNativeQueryByNamedParam() {
        List<Product> products = productRepository.findByDescription("product 1 description");
        products.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    void testNamedSQLQueries() {
        List<Product> products = productRepository.findByDescription("product 1 description");
        products.forEach(p -> System.out.println(p.toString()));

        List<Product> products1 = productRepository.findAllOrderByNameASC();
        products1.forEach(p -> System.out.println(p.toString()));
    }

}
