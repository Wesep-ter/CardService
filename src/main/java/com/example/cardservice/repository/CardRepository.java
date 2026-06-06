package com.example.cardservice.repository;

import com.example.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Long> {
    int countByUserId(Long userId);
    Optional<Card> findByCardNumber(String cardNumber);
}
