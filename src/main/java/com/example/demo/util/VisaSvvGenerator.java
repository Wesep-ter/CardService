package com.example.demo.util;

public class VisaSvvGenerator extends AbstractSvvGenerator {

    @Override
    public String generateSvv(String numberCard, String expiry, String secretCode) {
        String raw = numberCard + expiry + secretCode;
        return computeSvv(raw, "SHA-256");
    }
}
