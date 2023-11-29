package com.example.crm.repositories;

import com.example.crm.models.PhotoImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoImage, Long> {
}