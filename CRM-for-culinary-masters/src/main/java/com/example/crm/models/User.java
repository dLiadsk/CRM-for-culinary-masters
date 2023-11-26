package com.example.crm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private Long userId;

   @Column(name = "username")
   private String username;

   @Column(name = "email", unique = true)
   private String email;

   @Column(name = "password", length = 1000)
   private String password;

   @Column(name = "name")
   private String name;

   @Column(name = "surname")
   private String surname;

   @Column(name = "birthday")
   private Date birthday;

   @Column(name = "phone")
   private Integer phone;

   @Column(name = "country")
   private String country;

}
