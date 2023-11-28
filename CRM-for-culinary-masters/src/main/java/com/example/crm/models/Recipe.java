package com.example.crm.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
//   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//   @JoinColumn
//   private User user;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private Long recipeId;

   @Column(name = "name")
   private String name;

   @Column(name = "igridients")
   private String ingridients;

   @Column(name = "description")
   private String description;

   @Column(name = "notes")
   private String notes;

   @Column(name = "publish_date")
   private String image;

}
