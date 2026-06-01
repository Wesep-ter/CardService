package com.example.cardservice.dto;

import com.example.cardservice.constant.AccountType;
import com.example.cardservice.constant.Currency;
import com.example.cardservice.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long userId;
    private AccountType accountType;
    private String accountNumber;
    private String accountHolder;
    private Currency currency;
    private BigDecimal balance;
    private Status status;
    private LocalDateTime statusChangeTime;
}