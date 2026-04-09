package com.example.demo.service;

import com.example.demo.dto.CardData;
import com.example.demo.dto.CardDto;
import com.example.demo.dto.mapper.CardMapper;
import com.example.demo.entity.Card;
import com.example.demo.entity.NumberCardGenerator;
import com.example.demo.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final NumberCardGenerator numberCardGenerator;
    @Override
    public CardDto cardIssue(CardData cardData) {
        Card card = new Card();
        card.setCardNumber(numberCardGenerator.generateNumberCard(cardData.getPaymentSystem(),cardData.getUserId()));
        card.setCardholder(cardData.getCardholder());
        card.setCardType(cardData.getCardType());
        card.setBalance(BigDecimal.ZERO);
//        todo: Переделать.
//        card.setCurrency(cardData.getCurrency());
//        card.setExpirationDate(cardData.getExpirationDate());
//        card.setSvvSvc(passwordEncoder.encode(cardData.getSvvSvc()));
//        card.setActive(cardData.isActive());
        return CardMapper.toDto(userRepository.save(card));
    }

    @Override
    public CardDto getCardById(Long id) {
        return CardMapper.toDto(userRepository.getReferenceById(id));
    }
}
