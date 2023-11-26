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
   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
   @JoinColumn
   private User user;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private Long recipeId;

   @ManyToMany(mappedBy = "recipes")
   Set<Menu> menus;

   @Column(name = "name")
   private String name;

   @Column(name = "description")
   private String description;

   @Column(name = "cook_time")
   private String cookTime;

   @Column(name = "publish_date")
   private Date publishDate;

   @PrePersist
   public void init() {
       publishDate = new Date();
   }
}
