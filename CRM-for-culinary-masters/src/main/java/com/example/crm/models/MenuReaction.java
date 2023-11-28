//package com.example.crm.models;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Entity
//@Table(name = "MenuReactions")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class MenuReaction {
//   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//   @JoinColumn
//   private User user;
//   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//   @JoinColumn
//   private Menu menu;
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   @Column(name = "ID")
//   private Long reactionId;
//
//
//   @Column(name = "reaction")
//   private Byte reaction;
//
//
//}
