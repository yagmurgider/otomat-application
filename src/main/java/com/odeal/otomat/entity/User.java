package com.odeal.otomat.entity;

import com.odeal.otomat.abstacts.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_username", columnList = "username")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;

}
