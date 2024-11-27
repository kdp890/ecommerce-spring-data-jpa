package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Role;
import com.ecommerce.jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToManyUniDirectionalTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        User user = new User();
        user.setFirstName("Durga Prasad");
        user.setLastName("Kalamati");
        user.setEmail("kdp890@gmail.com");
        user.setPassword("userPassword");

        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        Role customer = new Role();
        customer.setName("ROLE_CUSTOMER");

        user.getRoles().add(admin);
        user.getRoles().add(customer);

        userRepository.save(user);
    }

    @Test
    void updateUser() {
        User user = userRepository.findById(1L).get();
        user.setFirstName("Durga");
        user.setEmail("kdp89017@gmail.com");

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        user.getRoles().add(roleUser);

        userRepository.save(user);

    }

    @Test
    void testFetchUser() {
        User user = userRepository.findById(1L).get();
        System.out.println(user.getEmail());
        user.getRoles().forEach((role) ->
        {
            System.out.println(role.getName());
        });
    }

    @Test
    void testDeleteUser() {
        userRepository.deleteById(1L);
    }
}
