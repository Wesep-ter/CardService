package com.example.cardService.service;

import com.example.cardService.constant.PaymentSystem;
import com.example.cardService.exception.ClientSegmentException;
import com.example.cardService.exception.IllegalBINException;
import com.example.cardService.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class NumberCardGenerator {

    @Value("${bank.bin}")
    private String BIN;
    @Value("${bank.secretKey}")
    private String secretKey;

    private final CardRepository cardRepository;

    public String generateNumberCard(PaymentSystem paymentSystem, Long userId){
        if (BIN.length() != 5 || !BIN.matches("\\d+")){
            throw new IllegalBINException("BIN должен состоять из 5 цифр.");
        }
        String first6 = paymentSystem.getNum() + BIN;
        String clientSegment = generateClientSegment(userId, cardRepository.countByUserId(userId));
        if (clientSegment == null){
            throw new ClientSegmentException("Ошибка генерации clientIdSegment");
        }
        String first15 = first6 + clientSegment;
        String lastDigit = String.valueOf(luhnCheckDigit(first15));

        return first15 + lastDigit;
    }

    private String generateClientSegment(Long userId, int cardCount) {
        String input = BIN + ":" + userId + ":" + cardCount;

        try {
            StringBuilder digits = new StringBuilder();
            byte[] hash = hmacSha256(input, secretKey);
            for (byte b : hash) {
                digits.append(String.format("%03d", Byte.toUnsignedInt(b)));
                if (digits.length() >= 9) break;
            }
            return digits.substring(0, 9);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private byte[] hmacSha256(String input, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"
        );
        mac.init(keySpec);
        return mac.doFinal(input.getBytes(StandardCharsets.UTF_8));
    }

    private int luhnCheckDigit(String first15) {
        if (first15.length() != 15) {
            throw new IllegalArgumentException("Нужно ровно 15 цифр, получено: " + first15.length());
        }
        int sum = 0;

        for (int i = 0; i < 15; i++) {
            int digit = Character.getNumericValue(first15.charAt(14 - i));
            if ((i + 1) % 2 == 1) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit;
    }
}
