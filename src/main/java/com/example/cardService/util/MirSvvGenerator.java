package com.example.cardService.util;

public class MirSvvGenerator extends AbstractSvvGenerator {

    @Override
    public String generateSvv(String numberCard, String expiry, String secretCode) {
        String raw = secretCode + expiry + numberCard;
        return computeSvv(raw, "SHA3-256");
    }
}
