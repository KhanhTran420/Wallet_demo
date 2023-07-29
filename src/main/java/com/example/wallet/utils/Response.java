package com.example.wallet.utils;

import lombok.Data;

@Data
public class Response {
    private String accountNumber;
    private String responseCode;
    private String responseMessage;
    private String accountName;
    private Double balance;
}
