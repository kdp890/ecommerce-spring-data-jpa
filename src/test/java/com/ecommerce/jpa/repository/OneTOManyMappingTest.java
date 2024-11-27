package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Address;
import com.ecommerce.jpa.entity.Order;
import com.ecommerce.jpa.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
public class OneTOManyMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    //save order along with also save it's order items

    @Test
    void testSaveOrder() {

        Order order = new Order();
        String trackingNumber = UUID.randomUUID().toString();
        order.setOrderTrackingNumber(trackingNumber.substring(0, 10));
        order.setStatus("In progress");
        //create order item 1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setQuantity(2);
        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(orderItem1.getQuantity())));
        orderItem1.setImageUrl(orderItem1.getProduct().getImageUrl());
        order.getOrderItems().add(orderItem1);

        //create order item 1
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(3);
        orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(orderItem2.getQuantity())));
        orderItem2.setImageUrl(orderItem2.getProduct().getImageUrl());
        order.getOrderItems().add(orderItem2);
        order.setTotalPrice(order.getTotalAmount());
        order.setTotalQuantity(2);

        Address address = new Address();
        address.setCity("Hyderabad");
        address.setState("Telangana");
        address.setStreet("Gowlidoddi");
        address.setCountry("India");
        address.setZipCode("411047");
        address.setOrderObj(order);

        order.setBillingAddress(address);

        orderRepository.save(order);
    }

    @Test
    void testFetchOrder() {
        Order order = orderRepository.findById(1L).get();
        System.out.println(order.toString());
    }

    @Test
    void testOrderMethod() {
        orderRepository.deleteById(1L);
    }
}
