package pl.shop.cart.entity;

import javax.persistence.*;

@Table(name = "tuser")
@Entity
public class UserEntity {

        @Column
        private String name;

        @Column
        private String surname;

        @Column
        private String login;

}
