package com.example.cardService.controller;

import com.example.cardService.dto.CardData;
import com.example.cardService.dto.CardDto;
import com.example.cardService.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/cardIssue")
    public ResponseEntity<CardDto> cardIssue(@RequestBody CardData cardData){
        CardDto body = cardService.cardIssue(cardData);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/getCardById/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable("id") Long id){
        CardDto body = cardService.getCardById(id);
        return ResponseEntity.ok(body);
    }
    @PostMapping("/getCardReissueById/{id}")
    public ResponseEntity<CardDto> getCardReissueById(@PathVariable("id")Long id){
        CardDto body = cardService.cardReissue(id);
        return ResponseEntity.ok(body);
    }
    @PostMapping("/changeCardStatus/{id}")
    public ResponseEntity<CardDto> changeCardStatus(CardData cardData, @PathVariable("id") Long id){
        CardDto body = cardService.changeCardStatus(cardData, id);
        return ResponseEntity.ok(body);
    }
}
