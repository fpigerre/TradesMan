package io.github.psgs.tradesman;

import io.github.psgs.tradesman.exceptions.InsufficientFundsException;
import io.github.psgs.tradesman.exceptions.NoInvestmentException;

import java.util.HashMap;
import java.util.UUID;

public class Exchange {
    UUID identifier;
    String name;
    float accountHoldings;
    float exchangeHoldings;
    float currentRate;
    HashMap<UUID, Float> investments;

    /**
     * @param identifier       A unique identifier to denominate the exchange
     * @param accountHoldings  The amount of account currency that the exchange possesses
     * @param exchangeHoldings The amount of exchange currency that the exchange possesses
     * @param currentRate      The exchange's current conversion rate
     */
    public Exchange(UUID identifier, String name, float accountHoldings, float exchangeHoldings, float currentRate) {
        this.identifier = identifier;
        this.name = name;
        this.accountHoldings = accountHoldings;
        this.exchangeHoldings = exchangeHoldings;
        this.currentRate = currentRate;

        investments = new HashMap<UUID, Float>();
    }

    /**
     * Retrieve the exchange's unique identifier
     *
     * @return The exchange's UUID object
     */
    public UUID getIdentifier() {
        return this.identifier;
    }

    /**
     * Retrieve the exchange's name
     *
     * @return The exchange's name as a string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Alter's the exchange's name
     *
     * @param newName The exchange's new name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Update the exchange's current conversion rate
     *
     * @param amount How many account currency the exchange's currency is worth
     */
    public void updateRate(float amount) {
        this.currentRate = amount;
    }

    /**
     * Get the exchange's current conversion rate
     *
     * @return How many account currency the exchange's currency is worth
     */
    public float getRate() {
        return this.currentRate;
    }

    /**
     * Buy a certain amount of exchange currency
     *
     * @param amount  The amount of exchange currency to buy
     * @param account The account in which to hold the investment
     */
    public void buy(float amount, Account account) throws InsufficientFundsException {
        if (investments.containsKey(account.getIdentifier())) {
            if (exchangeHoldings >= amount) {
                if (account.holdings > amount / currentRate) {
                    float currentInvestment = investments.get(account.getIdentifier());
                    investments.put(account.getIdentifier(), currentInvestment + amount);
                    account.withdraw(amount / currentRate);
                } else {
                    throw new InsufficientFundsException(account.getIdentifier(), "Account " + account.getIdentifier() + " does not have sufficient funds!");
                }
            } else {
                throw new InsufficientFundsException(getIdentifier(), "Exchange " + getIdentifier() + "does not have sufficient funds!");
            }
        } else {
            investments.put(account.getIdentifier(), amount);
            account.withdraw(amount / currentRate);
        }
    }

    /**
     * Sell a certain amount of exchange currency
     *
     * @param amount  The amount of exchange currency to sell
     * @param account The account from which to conduct the transaction
     * @throws NoInvestmentException
     * @throws InsufficientFundsException
     */
    public void sell(float amount, Account account) throws NoInvestmentException, InsufficientFundsException {
        if (investments.containsKey(account.getIdentifier())) {
            if (accountHoldings >= (amount * currentRate)) {
                if (investments.get(account.getIdentifier()) >= amount) {
                    float currentHoldings = investments.get(account.getIdentifier());
                    investments.put(account.getIdentifier(), currentHoldings - amount);
                    account.deposit(amount * currentRate);
                } else {
                    throw new InsufficientFundsException(account.getIdentifier(), "Account " + account.getIdentifier() + " does not have sufficient funds!");
                }
            } else {
                throw new InsufficientFundsException(getIdentifier(), "Exchange " + getIdentifier() + "does not have sufficient funds!");
            }
        } else {
            throw new NoInvestmentException(account.getIdentifier(), "No investments for " + account.getIdentifier() + " could be found!");
        }
    }

    /**
     * Liquidates all an account's investments
     *
     * @param account The account whose funds are to be liquidated
     * @throws InsufficientFundsException
     * @throws NoInvestmentException
     */
    public void removeFunds(Account account) throws InsufficientFundsException, NoInvestmentException {
        if (investments.containsKey(account.getIdentifier())) {
            sell(investments.get(account.getIdentifier()), account);
        }
    }
}
