package com.ecommerce.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@ToString
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique",
                        columnNames = "stock_keeping_unit")
        }
)
//@NamedQuery(
//        name="Product.findByPrice",
//        query = "select p from Product p where p.price=?1")
@NamedQueries(
        {
                @NamedQuery(
                        name = "Product.findAllOrderByNameDesc",
                        query = "SELECT p from Product p ORDER By p.name DESC"
                ),
                @NamedQuery(
                        name = "product.findByPrice",
                        query = "SELECT p from Product p where p.price=?1"
                )
        })
//@NamedNativeQuery(name = "Product.findByDescription",
//        query = "select * from products p where p.description=:description ",
//resultClass = Product.class )

@NamedNativeQueries(
        {
                @NamedNativeQuery(name = "Product.findByDescription",
                        query = "select * from products p where p.description=:description ",
                        resultClass = Product.class
                ),
                @NamedNativeQuery(
                        name = "Product.findAllOrderByNameASC",
                        query = "select * from products order by name asc",
                        resultClass = Product.class
                )


        }
)

//This Table annotation is used to set the table name and schema of the table for the entity class.
//And we can add uniqueConstraints for the table columns by adding in table annotation as above
public class Product {

    //There are four types of Primary Key generation .
    //They are AUTO,IDENTITY,SEQUENCE,TABLE
    //AUTO is default generation type and lets the persistence provide /database vendor to  choose the generation strategy.
    //If we use Hibernate as our persistence provide , it selects a generation strategy based on the database-specific dialect.
    //for most  popular databases , it selects GenerationType.SEQUENCE .
    //IDENTITY is used to generate the auto_increment id.
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_generator")
    @SequenceGenerator(
            name = "products_generator",
            sequenceName = "product_sequence_name",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "stock_keeping_unit", nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;

    private BigDecimal price;

    private boolean active;

    private String imageUrl;

    @CreationTimestamp()
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
