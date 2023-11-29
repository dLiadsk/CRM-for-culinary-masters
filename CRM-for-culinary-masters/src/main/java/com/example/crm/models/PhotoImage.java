package com.example.crm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Photo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoImage {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;

        @Column(name="fileName")
        private String fileName;
}
