package com.example.crm.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private Long menuId;

   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
   @JoinColumn
   private User user;

   @Column(name = "name")
   private String name;

   @Column(name = "notes")
   private String notes;

   @Column(name = "image")
   private String image;

   @Column(name = "preparationTime")
   private String preparationTime;

   @Column(name = "complexity")
   private String complexity;

   @ManyToMany
   @JoinTable(
           name = "menu_recipes",
           joinColumns = @JoinColumn(name = "menu_id"),
           inverseJoinColumns = @JoinColumn(name = "recipe_id"))
   List<Recipe> recipes;

   public static Menu of(User user,
                           String name,
                           String notes,
                           String image,
                           String preparationTime,
                           String complexity,
                           List<Recipe> recipes) {
      return new Menu(null, user,
              name,
              notes,
              image,
              preparationTime,
              complexity,
              recipes);
   }
}
