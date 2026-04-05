package com.example.demo.repository;

import com.example.demo.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Card,Integer> {
}
