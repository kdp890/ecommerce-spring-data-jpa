package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Address;
import com.ecommerce.jpa.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBiDirectionalTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void saveAddressMethod() {

        Address address = new Address();
        address.setCity("Hyderabad");
        address.setState("Telangana");
        address.setStreet("Gowlidoddi");
        address.setCountry("India");
        address.setZipCode("411047");

        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        address.setOrderObj(order);

        addressRepository.save(address);
    }

    @Test
    void updateAddressMethod() {
        Address address = addressRepository.findById(3L).get();
        address.setZipCode("510028");
        address.getOrderObj().setStatus("DELIVERED");

        addressRepository.save(address);
    }

    @Test
    void fetchAddressMethod() {
        Address address = addressRepository.findById(3L).get();
    }

    @Test
    void deleteAddressMethod() {
        addressRepository.deleteById(1L);
    }
}
