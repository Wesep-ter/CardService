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

    @PostMapping
    public ResponseEntity<CardDto> cardIssue(@RequestBody CardData cardData){
        CardDto body = cardService.cardIssue(cardData);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable("id") Long id){
        CardDto body = cardService.getCardById(id);
        return ResponseEntity.ok(body);
    }
    @PostMapping("/reissue/{id}")
    public ResponseEntity<CardDto> reissue(@PathVariable("id")Long id){
        CardDto body = cardService.cardReissue(id);
        return ResponseEntity.ok(body);
    }
    @PostMapping("/changeStatus/{id}")
    public ResponseEntity<CardDto> changeStatus(@RequestBody CardData cardData, @PathVariable("id") Long id){
        CardDto body = cardService.changeCardStatus(cardData, id);
        return ResponseEntity.ok(body);
    }
}
