package com.example.cardService.constant;

import com.example.cardService.util.MasterCardSvvGenerator;
import com.example.cardService.util.MirSvvGenerator;
import com.example.cardService.util.SvvSvsGenerator;
import com.example.cardService.util.VisaSvvGenerator;
import lombok.Getter;

@Getter
public enum PaymentSystem {
    VISA("VISA",4, new VisaSvvGenerator()),
    MASTERCARD("MASTERCARD",5, new MasterCardSvvGenerator()),
    MIR("MIR",2, new MirSvvGenerator());

    private final int num;
    private final String name;
    private final SvvSvsGenerator svvSvsGenerator;

    PaymentSystem(String name, int num, SvvSvsGenerator svvSvsGenerator) {
        this.name = name;
        this.num = num;
        this.svvSvsGenerator = svvSvsGenerator;
    }

}
