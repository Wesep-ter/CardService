package com.example.demo.service;

import com.example.demo.dto.CardData;
import com.example.demo.dto.CardDto;
public interface CardService {

    CardDto cardIssue(CardData cardData);
    CardDto getCardById(Long id);
    CardDto changeCardStatus(CardData cardData, Long cardId);
}
