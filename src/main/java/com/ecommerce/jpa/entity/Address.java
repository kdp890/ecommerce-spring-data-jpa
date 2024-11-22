package com.ecommerce.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order orderObj;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", order.id=" + orderObj.getId() +
                ", order.status=" + orderObj.getStatus() +
                ", order.orderTrackingNumber=" + orderObj.getOrderTrackingNumber() +
                ", order.dateCreated=" + orderObj.getDateCreated() +
                ", order.lastUpdated=" + orderObj.getLastUpdated() +
                ", order.totalPrice=" + orderObj.getTotalPrice() +
                ", order.totalQuantity=" + orderObj.getTotalQuantity() +
                '}';
    }
}
