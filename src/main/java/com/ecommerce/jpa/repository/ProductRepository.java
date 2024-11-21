package com.ecommerce.jpa.repository;

import com.ecommerce.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Returns the found product entry by using its name as search criteria
     * if no product is found ,this method returns null.
     *
     * @param name
     */
    Product findByName(String name);

    /**
     * Returns an Optional which contains the found product
     * entry by using its id as search criteria . If no product entry
     * is found , this method returns an empty Optional.
     *
     * @param id
     */
    Optional<Product> findById(Long id);

    /**
     * Returns the found list of product entries whose title or description
     * is given as a method parameter .If no product entries is found ,
     * this method returns an empty list.
     *
     * @param name
     * @param description
     * @return
     */
    List<Product> findByNameOrDescription(String name, String description);

    /**
     * Returns the found list of product entries whose name and description
     * is given as a method parameter .If no product entries is found ,
     * this method returns an empty list.
     *
     * @param name
     * @param description
     * @return
     */
    List<Product> findByNameAndDescription(String name, String description);


    /**
     * Return the distinct product entry whose name is given as a method parameter
     * If no product entry is found , this method returns null.
     *
     * @param name
     */
    Product findDistinctByName(String name);

    /**
     * Return the products whose price is greater than given price as method parameter
     *
     * @param price
     * @return
     */
    List<Product> findByPriceGreaterThan(BigDecimal price);

    /**
     * Return the products whose price to less than the given price as method parameter
     *
     * @param price
     * @return
     */
    List<Product> findByPriceLessThan(BigDecimal price);

    /**
     * Return the filtered the product records that match the given text
     *
     * @param name
     * @return
     */
    List<Product> findByNameContaining(String name);

    /**
     * Return the products based on SQL like conidition as like findByNameContaining
     *
     * @param name
     * @return
     */
    List<Product> findByNameLike(String name);

    /**
     * Returns products whose price between start price and end price
     *
     * @param startPrice
     * @param endPrice
     * @return
     */
    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    /**
     * Returns products whose dateCreated between start date and end date.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Returns list of products based on multiple values
     *
     * @param names
     * @return
     */
    List<Product> findByNameIn(List<String> names);

    /**
     * Return The first 2 products by name in ascending order
     *
     * @return
     */
    List<Product> findFirst2ByOrderByNameAsc();

    /**
     * Return the first 2 products by price in descending order
     *
     * @return
     */
    List<Product> findTop2ByOrderByPriceDesc();

    //Define JPQL query using @Query annotation with index or position
    //No need to follow the naming convention for the method.
    @Query("SELECT p from Product p where p.name=?1 or p.description=?2")
    List<Product> findByNameOrDescriptionJPQLIndexParam(String name, String description);

    //Define JPQL query using @Query annotation with Named Parameters
    @Query("SELECT p from Product p where p.name=:name and p.description=:description")
    Product findByNameAndDescriptionJPQLNamedParam(@Param("name") String name, @Param("description") String description);

    //Define Native SQL query using @Query annotation with index or position parameters
    @Query(value = "select * from products p where p.name=?1 and p.description=?2", nativeQuery = true)
    Product findByNameAndDescriptionSQLIndexParam(String name, String description);

    //Define Native SQL query using @Query annotation with named parameters
    @Query(value = "select * from products p where p.name=:name or p.description=:description", nativeQuery = true)
    List<Product> findByNameOrDescriptionSQLNamedParam(@Param("name") String name, @Param("description") String description);

    //Define named JPQL query with index or position parameter
    List<Product> findByPrice(BigDecimal price);

    //Define named query with named parameter
    List<Product> findAllOrderByNameDesc();

    //Define named native query with named parameter
    List<Product> findByDescription(@Param("description") String description);

    List<Product> findAllOrderByNameASC();
}
