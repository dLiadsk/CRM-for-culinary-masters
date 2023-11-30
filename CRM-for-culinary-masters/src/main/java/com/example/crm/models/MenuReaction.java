package com.example.crm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "MenuReactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long reactionId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
   @JoinColumn
   private User user;
   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
   @JoinColumn
   private Menu menu;


   @Column(name = "reaction")
   private String reaction;

   @Column(name = "disReaction")
   private String disReaction;
    public static MenuReaction of(User user,
                                    Menu menu,
                                    String reaction,
                                    String disReaction) {
        return new MenuReaction(null,user,menu,reaction,disReaction);
    }

}
