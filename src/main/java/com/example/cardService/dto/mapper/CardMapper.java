package com.example.cardService.dto.mapper;

import com.example.cardService.constant.PaymentSystem;
import com.example.cardService.dto.CardData;
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

    public static CardData toData(Card card){
        return CardData.builder()
                .cardholder(card.getCardholder())
                .userId(card.getUserId())
                .paymentSystem(PaymentSystem.getByNum(card.getCardNumber().charAt(0) - '0'))
                .cardStatus(card.getCardStatus())
                .cardType(card.getCardType())
                .currency(card.getCurrency())
                .build();
    }

}
