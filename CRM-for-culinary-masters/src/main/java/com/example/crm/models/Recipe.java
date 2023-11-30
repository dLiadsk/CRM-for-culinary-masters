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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long recipeId;


    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "igredients",columnDefinition = "TEXT")
    private String ingredients;

    @Column(name = "steps",columnDefinition = "TEXT")
    private String steps;

    @Column(name = "notes",columnDefinition = "TEXT")
    private String notes;
    @Column(name = "image")
    private String image;

    @Column(name = "preparation_time")
    private String preparationTime;

    @Column(name = "complexity")
    private String complexity;


    //
   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
   @JoinColumn
    private User user;

    public static Recipe of(String name,
                            String ingredients,
                            String steps,
                            String notes,
                            String image,
                            String preparationTime,
                            String complexity,
                            User user) {
        return new Recipe(null, name, ingredients, steps, notes, image, preparationTime, complexity, user);
    }

}
