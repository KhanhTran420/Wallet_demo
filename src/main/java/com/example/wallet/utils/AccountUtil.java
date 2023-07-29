package com.example.wallet.utils;

import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class AccountUtil {
    public static String ACCOUNT_CREATED = "000";
    public static String ACCOUNT_CREATED_MESSAGE = "Account Has been Successfully Created!";
    public static String DUPLICATE_ACCOUNT = "001";
    public static String DUPLICATE_ACCOUNT_MESSAGE = "User with this email address already has an account!";
    public static String INSUFFICIENT_BALANCE = "002";
    public static String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Balance";
    public static String DEBIT_SUCCEEDED = "003";
    public static String DEBIT_SUCCEEDED_MESSAGE= "Account debited successfully";


    public static String CREDIT_SUCCEEDED = "003";
    public static String CREDIT_SUCCEEDED_MESSAGE= "Account credited successfully";
    public static String ACCOUNT_NOT_FOUND = "004";
    public static String ACCOUNT_NOT_FOUND_MESSAGE= "Account Not found";

    public static String generateAccountNumber(){
        StringBuilder sb = new StringBuilder();
        //Year and 6 random digits
        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;


        System.out.println("Random value in int from "+min+" to "+max+ ":");
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println(random_int);
        String year = String.valueOf(currentYear);
        String randDigits = String.valueOf(random_int);
        return sb.append(year).append(randDigits).toString();

    }
}
