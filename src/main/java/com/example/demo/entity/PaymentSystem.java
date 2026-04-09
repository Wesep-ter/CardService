package com.example.demo.entity;

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

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
