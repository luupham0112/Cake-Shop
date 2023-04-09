package com.phanmem.cakeshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String userName;
    private String email;
    private String passWord;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = {@JoinColumn(name="USER_ID",referencedColumnName = "ID")},
             inverseJoinColumns= {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")}
    )
    private List<Role> roles;


    public User(User user) {

        this.userName =user.getUserName();
        this.email =user.getEmail();
        this.passWord = user.getPassWord();
        this.roles = user.getRoles();
    }
}
