package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NativeSQLQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameAndDescriptionSQLIndexParam() {
        Product product = productRepository.findByNameAndDescriptionSQLIndexParam("product 1", "product 1 description");
        System.out.println(product.toString());
    }

    @Test
    void findByNameAndDescriptionSQLNamedParam() {
        List<Product> products = productRepository.findByNameOrDescriptionSQLNamedParam("product 1", "product 2 description");
        products.forEach(p -> System.out.println(p.toString()));
    }
}
