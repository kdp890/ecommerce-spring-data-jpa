package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JPQLQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindByNameOrDescriptionJPQLIndexParam() {
        List<Product> products = productRepository.findByNameOrDescriptionJPQLIndexParam("product 1", "product 2 description");
        products.forEach(product -> System.out.println("Name:" + product.getName() + ", Description" + product.getDescription()));
    }

    @Test
    void testFindByNameAndDescriptionJPQLNamedParam() {
        Product product = productRepository.findByNameAndDescriptionJPQLNamedParam("product 1", "product 1 description");
        System.out.println(product.toString());
    }

}
