package com.example.cardservice.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResultEvent {
    private String transactionId;
    private String status;
    private String errorMessage;
}