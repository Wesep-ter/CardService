package com.example.cardservice.dto.mapper;

import com.example.cardservice.constant.PaymentSystem;
import com.example.cardservice.dto.CardData;
import com.example.cardservice.dto.CardDto;
import com.example.cardservice.entity.Card;

public class CardMapper {

    public static CardDto toDto(Card card){
        return CardDto.builder()
                .id(card.getId())
                .cardType(card.getCardType())
                .cardholder(card.getCardholder())
                .cardNumber(card.getCardNumber())
                .expirationDate(card.getExpirationDate())
                .cardStatus(card.getCardStatus())
                .accountId(card.getAccountId())
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
                .accountId(card.getAccountId())
                .build();
    }

}
