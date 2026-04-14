package com.example.cardService.service;

import com.example.cardService.dto.CardData;
import com.example.cardService.dto.CardDto;
import com.example.cardService.dto.mapper.CardMapper;
import com.example.cardService.entity.Card;
import com.example.cardService.util.*;
import com.example.cardService.exception.CardNotFoundException;
import com.example.cardService.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;
    private final NumberCardGenerator numberCardGenerator;
    @Value("${bank.svv.secretCode}")
    private String secretCode;

    @Override
    @Transactional
    public CardDto cardIssue(CardData cardData) {
        Card card = new Card();
        card.setCardNumber(numberCardGenerator.generateNumberCard(cardData.getPaymentSystem(),cardData.getUserId()));
        card.setCardholder(cardData.getCardholder());
        card.setCardType(cardData.getCardType());
        card.setBalance(BigDecimal.ZERO);
        card.setUserId(cardData.getUserId());
        card.setCurrency(cardData.getCurrency().name());
        card.setExpirationDate(LocalDate.now().plusYears(10));
        card.setSvvSvc(passwordEncoder.encode(generateSvv(card, cardData.getPaymentSystem().getSvvSvsGenerator())));
        card.setActive(true);
        card.setCardStatus(cardData.getCardStatus());
        return CardMapper.toDto(cardRepository.save(card));
    }

    @Override
    public CardDto getCardById(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Такой карты нет в базе данных."));
        return CardMapper.toDto(card);
    }

    @Override
    @Transactional
    public CardDto changeCardStatus(CardData cardData, Long cardId) {
        try {
            Card card = cardRepository.getReferenceById(cardId);
            if (!card.isActive()){
                log.info("Карта не активна, и статус не может быть изменён.");
                throw new RuntimeException();
            }
            card.setCardStatus(cardData.getCardStatus());
            return CardMapper.toDto(card);
        }catch (EntityNotFoundException _){
            log.info("Id карты не найдено.");
            throw new RuntimeException();
        }
    }

    private String generateSvv(Card card, SvvSvsGenerator svvSvsGenerator){
        return svvSvsGenerator.generateSvv(card.getCardNumber(),card.getExpirationDate().toString(),secretCode);
    }

}
