package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CardData {

    private Long cardId;

    private String cardType;

    private String cardholder;

    private String cardNumber;

    private LocalDate expirationDate;

    private String svvSvc;

    private BigDecimal balance;

    private String currency;

    private boolean isActive;

}
