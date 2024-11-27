package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Role;
import com.ecommerce.jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ManyToManyBiDirectionalTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testSaveRole() {

        User user = new User();
        user.setFirstName("Durga");
        user.setLastName("K");
        user.setEmail("durga.kalamati@gmail.com");
        user.setPassword("userPassword");

        User user1 = new User();
        user1.setFirstName("Sai");
        user1.setLastName("Bhommidi");
        user1.setEmail("sai.bhommidi@gmail.com");
        user1.setPassword("userPassword");

        Role employeeRole = new Role();
        employeeRole.setName("ROLE_EMPLOYEE");

        user.getRoles().add(employeeRole);
        user1.getRoles().add(employeeRole);

        employeeRole.getUsers().add(user);
        employeeRole.getUsers().add(user1);

        roleRepository.save(employeeRole);

    }

    @Test
    void testFetchRoles() {

        List<Role> roles = roleRepository.findAll();
        roles.forEach(r -> {
            System.out.println(r.getName());
            r.getUsers().forEach(u -> System.out.println(u.getFirstName()));
        });

    }
}
