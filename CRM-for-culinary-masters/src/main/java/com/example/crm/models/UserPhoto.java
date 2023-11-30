package com.example.crm.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "UsersPhotos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long photoId;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @Column(name = "image")
    private String image;

    public static UserPhoto of(User user,String image){
        return new UserPhoto(null,user,image);
    }
}
