package com.example.demo.controller;

import com.example.demo.dto.CardData;
import com.example.demo.dto.CardDto;
import com.example.demo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/cardIssue")
    public ResponseEntity<CardDto> cardIssue(CardData cardData){
        CardDto body = cardService.cardIssue(cardData);
        return ResponseEntity.ok(body);
    }
}
