package com.example.cardService.repository;

import com.example.cardService.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
    int countByUserId(Long userId);
}
