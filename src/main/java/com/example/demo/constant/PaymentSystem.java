package com.example.demo.constant;

import lombok.Getter;

@Getter
public enum PaymentSystem {
    VISA("VISA",4),
    MASTERCARD("MASTERCARD",5),
    MIR("MIR",2);

    private final int num;
    private final String name;

    PaymentSystem(String name, int num) {
        this.name = name;
        this.num = num;
    }

}
