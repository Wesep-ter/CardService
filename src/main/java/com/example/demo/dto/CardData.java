package com.example.demo.dto;

import com.example.demo.constant.CardStatus;
import com.example.demo.constant.Currency;
import com.example.demo.constant.PaymentSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardData {

    private Long id;

    private Long userId;

    private PaymentSystem paymentSystem;

    private String cardType;

    private String cardholder;

    private Currency currency;

    private CardStatus cardStatus;


}
