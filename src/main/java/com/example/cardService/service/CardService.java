package com.example.cardService.service;

import com.example.cardService.dto.CardData;
import com.example.cardService.dto.CardDto;
public interface CardService {

    CardDto cardIssue(CardData cardData);
    CardDto getCardById(Long id);
    CardDto changeCardStatus(CardData cardData, Long cardId);
    CardDto cardReissue(Long cardId);
}
