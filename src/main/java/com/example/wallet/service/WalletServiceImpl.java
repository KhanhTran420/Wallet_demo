package com.example.wallet.service;

import com.example.wallet.entity.Wallet;
import com.example.wallet.payload.CreditAccountRequest;
import com.example.wallet.payload.DebitAccountRequest;
import com.example.wallet.payload.WalletRequest;
import com.example.wallet.repository.WalletRepository;
import com.example.wallet.utils.AccountUtil;
import com.example.wallet.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    WalletRepository walletRepository;

    @Override
    public Response createWallet(WalletRequest walletRequest) {
        Response response = new Response();
        Wallet wallet = walletRepository.findByEmail(walletRequest.getEmail());
        final String accountNumber = AccountUtil.generateAccountNumber();
        if (wallet != null){
            response.setResponseCode(AccountUtil.DUPLICATE_ACCOUNT);
            response.setResponseMessage(AccountUtil.DUPLICATE_ACCOUNT_MESSAGE);
        }
        else {
            wallet = Wallet.builder()
                    .firstName(walletRequest.getFirstName())
                    .lastName(walletRequest.getLastName())
                    .middleName(walletRequest.getMiddleName())
                    .email(walletRequest.getEmail())
                    .accountNumber(accountNumber)
                    .balance(0.0)
                    .status("Active")
                    .build();
            walletRepository.save(wallet);

            response.setAccountName(walletRequest.getFirstName() + " " + walletRequest.getMiddleName() + "" + walletRequest.getLastName());
            response.setBalance(0.0);
            response.setResponseCode(AccountUtil.ACCOUNT_CREATED);
            response.setResponseMessage(AccountUtil.ACCOUNT_CREATED_MESSAGE);
            response.setAccountNumber(accountNumber);
        }
        return response;
    }

    @Override
    public Response debitAccount(DebitAccountRequest debitAccountRequest, String accountNumber) {
        Response response = new Response();
        Wallet wallet = walletRepository.findByAccountNumber(accountNumber);
        String accountName = wallet.getFirstName() + " " + wallet.getMiddleName() + "" + wallet.getLastName();

        if (wallet != null){
            Double initialBalance = wallet.getBalance();
            if (initialBalance >= debitAccountRequest.getAmount()){
                Double newBalance = initialBalance - debitAccountRequest.getAmount();
                wallet.setBalance(newBalance);

                response.setResponseCode(AccountUtil.DEBIT_SUCCEEDED);
                response.setResponseMessage(AccountUtil.DEBIT_SUCCEEDED_MESSAGE);
                response.setAccountNumber(accountNumber);
                response.setAccountName(accountName);
                response.setBalance(newBalance);
            }
            else {
                wallet.setBalance(initialBalance);
                walletRepository.save(wallet);
                response.setResponseCode(AccountUtil.INSUFFICIENT_BALANCE);
                response.setResponseMessage(AccountUtil.INSUFFICIENT_BALANCE_MESSAGE);
                response.setBalance(initialBalance);
                response.setAccountNumber(accountNumber);
                response.setAccountName(accountName);
            }
        }

        return response;
    }

    @Override
    public Response creditAccount(CreditAccountRequest creditAccountRequest, String accountNumber) {
        Response response = new Response();
        Wallet wallet = walletRepository.findByAccountNumber(accountNumber);


        if (wallet != null){
            Double initialBalance = wallet.getBalance();
            String accountName = wallet.getFirstName() + " " + wallet.getLastName() + " " + wallet.getMiddleName();
            Double newBalance = initialBalance + creditAccountRequest.getAmount();
            wallet.setBalance(newBalance);
            walletRepository.save(wallet);
            response.setResponseCode(AccountUtil.CREDIT_SUCCEEDED);
            response.setResponseMessage(AccountUtil.CREDIT_SUCCEEDED_MESSAGE);
            response.setAccountNumber(accountNumber);
            response.setAccountName(accountName);
            response.setBalance(newBalance);
        } else {
            response.setResponseCode(AccountUtil.ACCOUNT_NOT_FOUND);
            response.setResponseMessage(AccountUtil.ACCOUNT_NOT_FOUND_MESSAGE);

        }
        return response;
    }

    @Override
    public List<Wallet> viewAllAccount() {
        return walletRepository.findAll();
    }
}
