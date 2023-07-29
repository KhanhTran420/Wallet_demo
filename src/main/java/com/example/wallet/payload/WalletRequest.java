package com.example.wallet.payload;

import lombok.Data;

@Data
public class WalletRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
}
