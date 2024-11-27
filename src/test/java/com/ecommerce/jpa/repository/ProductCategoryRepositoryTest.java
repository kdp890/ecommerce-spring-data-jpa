package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Product;
import com.ecommerce.jpa.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Test
    void testSaveProductCategory() {

        ProductCategory category = new ProductCategory();
        category.setCategoryName("books");
        category.setCategoryDescription("books description");

        Product product1 = new Product();
        product1.setName("Core Java");
        product1.setPrice(new BigDecimal(1000));
        product1.setImageUrl("image1.png");
        product1.setSku("ABCD");
        product1.setActive(true);
        product1.setCategory(category);
        category.getProducts().add(product1);

        Product product2 = new Product();
        product2.setName("Advance Java");
        product2.setPrice(new BigDecimal(2000));
        product2.setImageUrl("image2.png");
        product2.setSku("ABCDE");
        product2.setActive(true);
        product2.setCategory(category);
        category.getProducts().add(product2);

        categoryRepository.save(category);

    }

    @Test
    @Transactional
    void testFetchProductCategory() {
        ProductCategory category = categoryRepository.findById(1L).get();
        System.out.println(category);
        System.out.println(category.getProducts());
    }


}