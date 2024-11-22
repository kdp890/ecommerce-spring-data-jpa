package com.ecommerce.jpa.repository;


import com.ecommerce.jpa.entity.Address;
import com.ecommerce.jpa.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUniDirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveOrderMethod() {
        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        Address address = new Address();
        address.setCity("Hyderabad");
        address.setState("Telangana");
        address.setStreet("Gowlidoddi");
        address.setCountry("India");
        address.setZipCode("411047");

        order.setBillingAddress(address);

        orderRepository.save(order);

    }

    @Test
    void getOrderMethod() {
        Order order = orderRepository.findById(1L).get();
        System.out.println(order);
    }

    @Test
    void updateOrderMethod() {
        Order order = orderRepository.findById(1L).get();
        order.setStatus("DELIVERED");
        order.getBillingAddress().setZipCode("520001");
        orderRepository.save(order);

    }

    @Test
    void testDeleteOrder() {
        orderRepository.deleteById(1L);
    }
}
