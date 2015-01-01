package io.github.psgs.tradesman.exceptions;

import java.util.UUID;

public class InsufficientFundsException extends Exception {
    private UUID identifier;

    public InsufficientFundsException(UUID identifier) {
        super();
        this.identifier = identifier;
    }

    public InsufficientFundsException(UUID identifier, String message) {
        super(message);
        this.identifier = identifier;
    }

    public InsufficientFundsException(UUID identifier, String message, Throwable cause) {
        super(message, cause);
        this.identifier = identifier;
    }

    public InsufficientFundsException(UUID identifier, Throwable cause) {
        super(cause);
        this.identifier = identifier;
    }

    public UUID getIdentifier() {
        return this.identifier;
    }
}
