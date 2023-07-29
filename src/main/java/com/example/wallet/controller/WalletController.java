package com.example.wallet.controller;

import com.example.wallet.entity.Wallet;
import com.example.wallet.payload.CreditAccountRequest;
import com.example.wallet.payload.DebitAccountRequest;
import com.example.wallet.payload.WalletRequest;
import com.example.wallet.service.WalletService;
import com.example.wallet.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @PostMapping("/createAccount")
    public Response createAccount(@Valid @RequestBody WalletRequest walletRequest){
        return walletService.createWallet(walletRequest);
    }

    @PutMapping("/debit/{accountNumber}")
    public Response debitAccount(@Valid @RequestBody DebitAccountRequest debitAccountRequest, @PathVariable String accountNumber){
        return walletService.debitAccount(debitAccountRequest,accountNumber);
    }

    @PutMapping("/credit/{accountNumber}")
    public Response creditAccount(@Valid @RequestBody CreditAccountRequest creditAccountRequest, @PathVariable String accountNumber){
        return walletService.creditAccount(creditAccountRequest,accountNumber);
    }

    @GetMapping("/accounts")
    public List<Wallet> fetchAccounts(){
        return walletService.viewAllAccount();
    }
}
