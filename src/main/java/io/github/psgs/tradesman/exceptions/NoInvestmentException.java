package io.github.psgs.tradesman.exceptions;

import java.util.UUID;

public class NoInvestmentException extends Exception {
    private UUID identifier;

    public NoInvestmentException(UUID identifier) {
        super();
        this.identifier = identifier;
    }

    public NoInvestmentException(UUID identifier, String message) {
        super(message);
        this.identifier = identifier;
    }

    public NoInvestmentException(UUID identifier, String message, Throwable cause) {
        super(message, cause);
        this.identifier = identifier;
    }

    public NoInvestmentException(UUID identifier, Throwable cause) {
        super(cause);
        this.identifier = identifier;
    }

    public UUID getIdentifier() {
        return this.identifier;
    }
}
