package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QueryMethodTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindByName() {
        Product product = productRepository.findByName("product 1");
        System.out.println(product.toString());
    }

    @Test
    void testFindByIdMethod() {
        Product product = productRepository.findById(8L).get();
        System.out.println(product.toString());

    }

    @Test
    void testFindByNameOrDescription() {
        List<Product> products = productRepository.findByNameOrDescription("product 1", "product 1 desc");
        products.forEach((p) -> System.out.println(p.getName()));
    }

    @Test
    void testFindByNameAndDescription() {
        List<Product> products = productRepository.findByNameAndDescription("product 1", "product 1 description");
        products.forEach((p) ->
        {
            System.out.println(p.getName());
            System.out.println(p.getId());

        });
    }

    @Test
    void testFindDistinctByName() {
        Product product = productRepository.findDistinctByName("product 1");
        System.out.println(product.toString());
    }

    @Test
    void testFindByPriceGreaterThan() {
        List<Product> product = productRepository.findByPriceGreaterThan(new BigDecimal(100));
        product.forEach((p) -> System.out.println(p.toString()));
    }

    @Test
    void testFindByPriceLessThan() {
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(200));
        products.forEach(p -> System.out.println(p.getPrice()));
    }

    @Test
    void testFindByNameContaining() {
        List<Product> products = productRepository.findByNameContaining("product");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    void testFindByNameLike() {
        List<Product> products = productRepository.findByNameLike("product");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    void testFindByPriceBetween() {
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal(100), new BigDecimal(300));
        products.forEach(p -> System.out.println(p.getPrice()));
    }

    @Test
    void testFindByDateCreatedBetween() {
        LocalDateTime startDate = LocalDateTime.of(2024, 10, 30, 12, 0, 0);
        List<Product> products = productRepository.findByDateCreatedBetween(startDate, LocalDateTime.now());
        products.forEach(p -> System.out.println(p.getDateCreated().toString()));
    }


    @Test
    void testFindByNameIn() {
        List<Product> products = productRepository.findByNameIn(List.of("product 1", "product 2", "product 3"));
        products.forEach(p -> System.out.println("Name:" + p.getName() + ",Id:" + p.getId()));
    }

    @Test
    void testFindFirst2OrderByNameAsc() {
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();
        products.forEach(p -> System.out.println("ProductName:" + p.getName() + ", Id:" + p.getId()));
    }

    @Test
    void testFindTop2ByOrderPriceDesc() {
        List<Product> products = productRepository.findTop2ByOrderByPriceDesc();
        products.forEach(p -> System.out.println("Price:" + p.getPrice()));
    }
}
