package com.example.demo.service;

import com.example.demo.dto.CardData;
import com.example.demo.dto.CardDto;
import com.example.demo.dto.mapper.CardMapper;
import com.example.demo.entity.Card;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public CardDto cardIssue(CardData cardData) {
        Card card = new Card();
        card.setCardId(cardData.getCardId());
        card.setCardNumber(cardData.getCardNumber());
        card.setCardholder(cardData.getCardholder());
        card.setCardType(cardData.getCardType());
        card.setBalance(cardData.getBalance());
        card.setCurrency(cardData.getCurrency());
        card.setExpirationDate(cardData.getExpirationDate());
        card.setSvvSvc(passwordEncoder.encode(cardData.getSvvSvc()));
        card.setActive(cardData.isActive());
        return CardMapper.toDto(userRepository.save(card));
    }

    @Override
    public CardDto getCardById(Integer id) {
        return CardMapper.toDto(userRepository.getReferenceById(id));
    }
}
