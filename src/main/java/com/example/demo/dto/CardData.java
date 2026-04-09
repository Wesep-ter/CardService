package com.example.demo.dto;

import com.example.demo.entity.PaymentSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CardData {

    private Long id;

    private Long userId;

    private PaymentSystem paymentSystem;

    private String cardType;

    private String cardholder;


}
