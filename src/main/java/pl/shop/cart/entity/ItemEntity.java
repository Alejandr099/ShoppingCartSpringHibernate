package pl.shop.cart.entity;

import javax.persistence.*;

@Table(name = "titem")
@Entity
public class ItemEntity {

    @Column
    private int id;

    @Column
    private String itemName;

    @Column
    private double price;

    @Column
    private int quantity;
}
