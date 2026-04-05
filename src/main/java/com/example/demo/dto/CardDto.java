package com.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CardDto {

    private Long cardId;

    private String cardType;

    private String cardholder;

    private String cardNumber;

    private LocalDate expirationDate;

    private boolean isActive;

}
