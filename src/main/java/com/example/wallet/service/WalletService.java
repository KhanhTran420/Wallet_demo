package com.example.wallet.service;

import com.example.wallet.entity.Wallet;
import com.example.wallet.payload.CreditAccountRequest;
import com.example.wallet.payload.DebitAccountRequest;
import com.example.wallet.payload.WalletRequest;
import com.example.wallet.utils.Response;

import java.util.List;

public interface WalletService {

    Response createWallet(WalletRequest walletRequest);
    Response debitAccount(DebitAccountRequest debitAccountRequest, String accountNumber);
    Response creditAccount(CreditAccountRequest creditAccountRequest, String accountNumber);

    List<Wallet> viewAllAccount();
}
