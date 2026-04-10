package com.example.demo.dto.mapper;

import com.example.demo.dto.CardDto;
import com.example.demo.entity.Card;

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
