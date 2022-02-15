package net.groshev.quote.rest;

public class Response {

    private final String message;

    Response(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
