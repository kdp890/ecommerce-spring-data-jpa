package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testPageable() {
        int pageNo = 0;
        int pageSize = 5;

        //create pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        //call findAll method and pass pageable object.
        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();

        products.forEach(p -> System.out.println(p.toString()));
        //total pages

        int totalPage = page.getTotalPages();
        //total elements
        long totalElements = page.getTotalElements();
        //number of elements
        int numberOfElements = page.getNumberOfElements();
        //size
        int size = page.getSize();
        //last
        boolean isLast = page.isLast();
        //first
        boolean isFirst = page.isFirst();
        System.out.println("total page:" + totalPage);
        System.out.println("total elements:" + totalElements);
        System.out.println("numberOfElements:" + numberOfElements);
        System.out.println("size:" + size);
        System.out.println("isLast:" + isLast);
        System.out.println("isFirst:" + isFirst);
    }

    //sorting consists of two fields sort by and sort direction
    @Test
    void sorting() {
        //by default the direction is ascending order
        String sortBy = "price";
//        List<Product> products=productRepository.findAll(Sort.by(sortBy).descending());
//        List<Product> products=productRepository.findAll(Sort.by(sortBy).ascending());
        List<Product> products = productRepository.findAll(Sort.by(sortBy));
        products.forEach(p -> System.out.println(p.toString()));

        //If suppose that we are getting the order value from client
        String sortDir = "desc";
        Sort clientSort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Product> clientSortingProducts = productRepository.findAll(clientSort);
        clientSortingProducts.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    void testSortingByMultipleFields() {
        String sortByName = "name";
        String sortByDescription = "description";
        String sortDir = "desc";

        Sort sortByNameObj = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortByName).ascending() : Sort.by(sortByName).descending();
        Sort sortByDescriptionObj = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortByDescription).ascending() : Sort.by(sortByDescription).descending();

        Sort groupBySort = sortByNameObj.and(sortByDescriptionObj);
        List<Product> products = productRepository.findAll(groupBySort);

        products.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    void testPaginationAndSortingTogether() {

        String sortBy = "price";
        String sortDirection = "desc";
        int pageNo = 1;
        int pageSize = 5;
        //sort object
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //Pageable Object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page page = productRepository.findAll(pageable);
        List<Product> products = page.getContent();
        products.forEach(p -> System.out.println(p.toString()));

        int totalPage = page.getTotalPages();
        //total elements
        long totalElements = page.getTotalElements();
        //number of elements
        int numberOfElements = page.getNumberOfElements();
        //size
        int size = page.getSize();
        //last
        boolean isLast = page.isLast();
        //first
        boolean isFirst = page.isFirst();
        System.out.println("total page:" + totalPage);
        System.out.println("total elements:" + totalElements);
        System.out.println("numberOfElements:" + numberOfElements);
        System.out.println("size:" + size);
        System.out.println("isLast:" + isLast);
        System.out.println("isFirst:" + isFirst);

    }

}
