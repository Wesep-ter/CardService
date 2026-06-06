package com.example.cardservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardTransferEvent{

    private String transactionId;

    private String sourceCardNumber;

    private String targetCardNumber;

    private BigDecimal amount;

    private String currency;
}