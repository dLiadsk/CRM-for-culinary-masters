package com.example.crm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "RecipeReactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeReaction {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private Long reactionId;
   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
   @JoinColumn
   private User user;
   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
   @JoinColumn
   private Recipe recipe;


   @Column(name = "reaction")
   private String reaction;

   @Column(name = "disReaction")
   private String disReaction;


   public static RecipeReaction of(User user,
                                   Recipe recipe,
                                   String reaction,
                                   String disReaction) {
      return new RecipeReaction(null,user,recipe,reaction,disReaction);
   }

}