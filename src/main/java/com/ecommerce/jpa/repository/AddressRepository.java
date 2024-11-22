package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
