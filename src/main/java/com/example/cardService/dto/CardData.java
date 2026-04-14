package com.example.cardService.dto;

import com.example.cardService.constant.CardStatus;
import com.example.cardService.constant.Currency;
import com.example.cardService.constant.PaymentSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardData {

    private Long userId;

    private PaymentSystem paymentSystem;

    private String cardType;

    private String cardholder;

    private Currency currency;

    private CardStatus cardStatus;
}
