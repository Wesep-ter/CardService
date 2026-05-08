package com.example.cardservice.dto;

import com.example.cardservice.constant.CardStatus;
import com.example.cardservice.constant.Currency;
import com.example.cardservice.constant.PaymentSystem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CardData {

    private Long userId;

    private Long accountId;

    private PaymentSystem paymentSystem;

    private String cardType;

    private String cardholder;

    private Currency currency;

    private CardStatus cardStatus;
}
