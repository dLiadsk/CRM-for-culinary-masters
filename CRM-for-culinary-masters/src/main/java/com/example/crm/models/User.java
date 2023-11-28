package com.example.crm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private Long userId;

   @Column(name = "username")
   private String username;

   @Column(name = "email")
   private String email;

   @Column(name = "password", length = 1000)
   private String password;

   @Column(name = "name")
   private String firstName;

   @Column(name = "surname")
   private String lastName;

   @Column(name = "birthday")
   private String date;

   @Column(name = "phone")
   private String phoneNumber;

   @Column(name = "father")
   private String father;

   @Column(name = "gender")
   private String gender;

   public static User of(String username,String email,String password,
                         String firstName,String lastName,String date,
                         String phoneNumber,String father,String gender){
      return new User(null,username,email,password,firstName,lastName,date,phoneNumber,father,gender);
   }
}
