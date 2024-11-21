package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSave() {
        //create product
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");
        //save product
        Product result = productRepository.save(product);
        //display product info
        System.out.println(result.getId());
        System.out.println(result.toString());
    }

    @Test
    void testFindByIdMethod() {
        Long id = 1L;
        Product product = productRepository.findById(id).get();

    }

    @Test
    void testSaveAllMethod() {
        Product product2 = new Product();
        product2.setName("product 2");
        product2.setDescription("product 2 description");
        product2.setSku("100ABCD");
        product2.setPrice(new BigDecimal(200));
        product2.setActive(true);
        product2.setImageUrl("product2.png");

        Product product3 = new Product();
        product3.setName("product 3");
        product3.setDescription("product 3 description");
        product3.setSku("100ABCDE");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product3.png");

        productRepository.saveAll(List.of(product2, product3));

    }

    @Test
    void testFindAllMethod() {
        List<Product> products = productRepository.findAll();
        products.forEach((p) ->
                System.out.println(p.getName()));
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        productRepository.deleteById(id);
    }

    @Test
    void testDelete() {
        //find by id
        Long id = 2L;
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Test
    void testDeleteAll() {
        //  productRepository.deleteAll();
        Product product1 = productRepository.findById(5L).get();
        Product product2 = productRepository.findById(6L).get();
        Product product3 = productRepository.findById(7L).get();
        productRepository.deleteAll(List.of(product1, product2, product3));
    }

    @Test
    void testCount() {
        long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsById() {
        boolean isentityExists = productRepository.existsById(8L);
        System.out.println(isentityExists);
    }


}