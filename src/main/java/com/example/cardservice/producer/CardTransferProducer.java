package com.example.cardservice.producer;

import com.example.cardservice.event.CardValidatedEvent;
import com.example.cardservice.event.TransferResultEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CardTransferProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendValidatedData(CardValidatedEvent event) {
        log.info("Карты проверены. Отправка данных транзакции {} в топик 'card-validated-events'", event.getTransactionId());
        kafkaTemplate.send("card-validated-events", event.getTransactionId(), event);
    }
    public void sendTransferError(TransferResultEvent errorEvent) {
        log.warn("Отправка сообщения об ошибке валидации карт в топик 'transfer-results'");
        kafkaTemplate.send("transfer-results", errorEvent.getTransactionId(), errorEvent);
    }
}
