package pl.shop.cart.entity;


import pl.shop.cart.repo.UserRepoEntity;

import javax.persistence.*;

@Table(name = "torder")
@Entity
public class OrderEntity {

    @Column
    private int id;

    @JoinColumn(name = "user")
    private UserRepoEntity userEntity;
}
