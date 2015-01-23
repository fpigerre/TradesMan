package io.github.psgs.tradesman;

import io.github.psgs.tradesman.exceptions.InsufficientFundsException;
import io.github.psgs.tradesman.exceptions.NoInvestmentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.UUID;

public class TradesMan {

    public static Account defaultAccount;
    public static Exchange defaultExchange;

    public static void main(String[] args) {
        defaultAccount = new Account(UUID.randomUUID(), 1000, new HashMap<UUID, Float>());
        defaultExchange = new Exchange(UUID.randomUUID(), "Default", 1000, 1000, 1);
        getCommand();
    }

    public static void getCommand() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String[] arguments = bufferedReader.readLine().split(" ");

            if (arguments[0].equalsIgnoreCase("buy")) {
                if (arguments.length >= 2) {
                    float amount = Float.valueOf(arguments[1]);
                    defaultAccount.invest(amount, defaultExchange);
                } else {
                    throw new IllegalArgumentException();
                }
            }

            if (arguments[0].equalsIgnoreCase("sell")) {
                if (arguments.length >= 2) {
                    float amount = Float.valueOf(arguments[1]);
                    try {
                        defaultExchange.sell(amount, defaultAccount);
                    } catch (InsufficientFundsException inEx) {
                        System.err.println(inEx.getMessage());
                    } catch (NoInvestmentException noEx) {
                        System.err.println(noEx.getMessage());
                    }
                } else {
                    throw new IllegalArgumentException();
                }

            }
        } catch (IOException ex) {
            System.err.println("An error has occurred!");
            System.out.println(ex.getMessage());
        }
        getCommand();
    }
}
