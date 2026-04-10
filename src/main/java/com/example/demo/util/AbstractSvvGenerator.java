package com.example.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public abstract class AbstractSvvGenerator implements SvvSvsGenerator {

    protected String computeSvv(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            int num = Math.abs(((hash[0] & 0xFF) << 16)
                    | ((hash[1] & 0xFF) << 8)
                    |  (hash[2] & 0xFF)) % 1000;
            return String.format("%03d", num);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка генерации SVV", e);
        }
    }
}