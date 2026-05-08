package com.example.cardservice.service;

import com.example.cardservice.dto.CardData;
import com.example.cardservice.dto.CardDto;
public interface CardService {

    CardDto cardIssue(CardData cardData);
    CardDto getCardById(Long id);
    CardDto changeCardStatus(CardData cardData, Long cardId);
    CardDto cardReissue(Long cardId);
}
