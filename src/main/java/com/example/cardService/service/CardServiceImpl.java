package com.example.cardService.service;

import com.example.cardService.constant.CardStatus;
import com.example.cardService.dto.CardData;
import com.example.cardService.dto.CardDto;
import com.example.cardService.dto.mapper.CardMapper;
import com.example.cardService.entity.Card;
import com.example.cardService.exception.*;
import com.example.cardService.util.*;
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
        Card card = createCard(cardData);
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
                throw new InactiveCardException("Карта не активна, и статус не может быть изменён.");
            }
            if (cardData.getCardStatus() == CardStatus.REISSUE){
                log.info("Карта активна, но требует перевыпуска.");
                throw new CardStatusException("Карта активна, но требует перевыпуска.");
            }
            card.setCardStatus(cardData.getCardStatus());
            return CardMapper.toDto(card);
        }catch (EntityNotFoundException _){
            log.info("Id карты не найдено.");
            throw new CardIdException("Id карты не найдено.");
        }
    }

    @Override
    @Transactional
    public CardDto cardReissue(Long cardId) {
        Card card = cardRepository.getReferenceById(cardId);
        Card reissuedCard;
        if (!card.isActive()){
            log.info("Карта не активна, перевыпуск невозможен.");
            throw new InactiveCardException("Карта не активна, перевыпуск невозможен.");
        }
        if (card.getCardStatus() == CardStatus.REISSUE){
            reissuedCard = createCard(CardMapper.toData(card));
            card.setActive(false);
        }else throw new CardReissueException("Статус карты: " + card.getCardStatus() + ". А для перевыпуска карты требуется статус: REISSUE");
        return CardMapper.toDto(cardRepository.save(reissuedCard));
    }

    private String generateSvv(Card card, SvvSvsGenerator svvSvsGenerator){
        return svvSvsGenerator.generateSvv(card.getCardNumber(),card.getExpirationDate().toString(),secretCode);
    }

    private Card createCard(CardData cardData) {
        Card card = Card.builder()
                .cardNumber(numberCardGenerator.generateNumberCard(cardData.getPaymentSystem(),cardData.getUserId()))
                .cardholder(cardData.getCardholder())
                .balance(BigDecimal.ZERO)
                .userId(cardData.getUserId())
                .currency(cardData.getCurrency())
                .expirationDate(LocalDate.now().plusYears(10))
                .isActive(true)
                .cardStatus(cardData.getCardStatus())
                .build();
        card.setSvvSvc(passwordEncoder.encode(generateSvv(card, cardData.getPaymentSystem().getSvvSvsGenerator())));
        return card;
    }

}
