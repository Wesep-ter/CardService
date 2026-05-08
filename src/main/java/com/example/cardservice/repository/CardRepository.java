package com.example.cardservice.repository;

import com.example.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
    int countByUserId(Long userId);
}
