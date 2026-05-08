package com.example.cardservice.dto;
import com.example.cardservice.constant.CardStatus;
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

    private Long id;

    private Long accountId;

    private String cardType;

    private String cardholder;

    private String cardNumber;

    private LocalDate expirationDate;

    private CardStatus cardStatus;
}
