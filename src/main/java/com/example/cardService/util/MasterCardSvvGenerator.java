package com.example.cardService.util;

public class MasterCardSvvGenerator extends AbstractSvvGenerator {

    @Override
    public String generateSvv(String numberCard, String expiry, String secretCode) {
        String salt = numberCard.substring(numberCard.length() - 4);
        String raw = salt + secretCode + expiry + numberCard;
        return computeSvv(raw, "SHA-512");
    }
}
