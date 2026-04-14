package com.example.cardService.dto.mapper;

import com.example.cardService.dto.CardDto;
import com.example.cardService.entity.Card;

public class CardMapper {

    public static CardDto toDto(Card card){
        return CardDto.builder()
                .id(card.getId())
                .cardType(card.getCardType())
                .cardholder(card.getCardholder())
                .cardNumber(card.getCardNumber())
                .expirationDate(card.getExpirationDate())
                .cardStatus(card.getCardStatus())
                .build();
    }

}
