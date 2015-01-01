package io.github.psgs.tradesman;

import io.github.psgs.tradesman.exceptions.InsufficientFundsException;

import java.util.HashMap;
import java.util.UUID;

public class Account {
    UUID identifier;
    float holdings;
    HashMap<UUID, Float> protectedHoldings;

    /**
     * @param identifier        A unique identifier to denominate the account
     * @param holdings          The amount of account currency that the account possesses
     * @param protectedHoldings The amount of account currency that is currently stowed for safekeeping
     */
    public Account(UUID identifier, float holdings, HashMap<UUID, Float> protectedHoldings) {
        this.identifier = identifier;
        this.holdings = holdings;
        this.protectedHoldings = protectedHoldings;
    }

    /**
     * Retrieve the account's unique identifier
     *
     * @return The account's UUID object
     */
    public UUID getIdentifier() {
        return this.identifier;
    }

    /**
     * Deposit an amount of currency into an account
     *
     * @param amount The amount of currency to deposit
     */
    public void deposit(float amount) {
        this.holdings += amount;
    }

    /**
     * Withdraw an amount of currency from an account
     *
     * @param amount The amount of currency to withdraw
     */
    public void withdraw(float amount) {
        this.holdings -= amount;
    }

    /**
     * Invest in a particular currency
     *
     * @param amount   The amount of account holder's currency to invest
     * @param exchange A currency exchange
     */
    public void invest(float amount, Exchange exchange) {
        float protection = amount / 10;
        amount = amount - protection;
        try {
            if (protectedHoldings.containsKey(exchange.getIdentifier())) {
                float currentProtection = protectedHoldings.get(exchange.getIdentifier());
                protectedHoldings.put(exchange.getIdentifier(), currentProtection + protection);
            } else {
                protectedHoldings.put(exchange.getIdentifier(), protection);
            }
            exchange.buy(amount, this);
            withdraw(amount);
        } catch (InsufficientFundsException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
