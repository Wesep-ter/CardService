package com.example.cardservice.consumer;

import com.example.cardservice.entity.Card;
import com.example.cardservice.event.CardTransferEvent;
import com.example.cardservice.event.CardValidatedEvent;
import com.example.cardservice.event.TransferResultEvent;
import com.example.cardservice.producer.CardTransferProducer;
import com.example.cardservice.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CardTransferConsumer {

    private final CardRepository cardRepository;
    private final CardTransferProducer cardTransferProducer;

    @KafkaListener(topics = "card-transfer-requests", groupId = "card-service-group")
    public void handleCardTransferRequest(CardTransferEvent event){
        log.info("Получен запрос на проверку карт для транзакции: {}", event.getTransactionId());
        String topicName = "card-transfer-requests";

        try {
            Card sourceCard = cardRepository.findByCardNumber(event.getSourceCardNumber())
                    .orElseThrow(() -> new RuntimeException("Карта отправителя не найдена"));
            if (!sourceCard.isActive()) {
                throw new RuntimeException("Карта отправителя заблокирована");
            }

            Card targetCard = cardRepository.findByCardNumber(event.getTargetCardNumber())
                    .orElseThrow(() -> new RuntimeException("Карта получателя не найдена"));
            if (!targetCard.isActive()) {
                throw new RuntimeException("Карта получателя заблокирована");
            }

            CardValidatedEvent cardValidatedEvent = new CardValidatedEvent().builder()
                    .transactionId(event.getTransactionId())
                    .sourceAccountId(sourceCard.getAccountId())
                    .targetAccountId(targetCard.getAccountId())
                    .amount(event.getAmount())
                    .currency(event.getCurrency())
                    .build();

            cardTransferProducer.sendValidatedData(cardValidatedEvent);
        }
        catch (Exception ex){
            log.error("Ошибка валидации карт для транзакции {}: {}", event.getTransactionId(), ex.getMessage());

            TransferResultEvent errorEvent = TransferResultEvent.builder()
                    .transactionId(event.getTransactionId())
                    .status("FAILED")
                    .errorMessage(ex.getMessage())
                    .build();
            cardTransferProducer.sendTransferError(errorEvent);
        }

    }
}
