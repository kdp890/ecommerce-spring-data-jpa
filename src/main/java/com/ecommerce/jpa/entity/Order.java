package com.ecommerce.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderTrackingNumber;

    private int totalQuantity;

    private BigDecimal totalPrice;

    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    /*
     *
     *OneToOne UniDirectional Mapping , primary key of addresses table defined as foreign key in Orders Table.
     *Reference of Address will be created in Order entity. But Order reference will not be created in Address entity.
     *Here in OneToOne uniDirectional , we are defining @JoinCloumn in Source Entity (Order entity)
     */
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
//    private Address billingAddress;

    //mappedBy attribute is mandatory to map the Address entity. The value should be the name of the Order entity reference name in Address Entity class.
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderObj")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();

    //If we don't override the toString which is already defined by lombok , It will recursively call the toString from Address class order reference.
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", billingAddress.id=" + billingAddress.getId() +
                ", billingAddress.street=" + billingAddress.getStreet() +
                ", billingAddress.city=" + billingAddress.getCity() +
                ", billingAddress.state=" + billingAddress.getState() +
                ", billingAddress.country=" + billingAddress.getCountry() +
                ", billingAddress.zipCode=" + billingAddress.getZipCode() +
                '}';
    }
}
