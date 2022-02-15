package net.groshev.quote.api;

import net.groshev.quote.common.Quote;
import net.groshev.quote.common.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class QuotesHandler {

    public static final String UID_NOT_FOUND_ERROR = "uid not found, error = ";

    private final QuoteService service;


    @Autowired
    public QuotesHandler(final QuoteService service) {
        this.service = service;
    }

    public Mono<ServerResponse> getQuotesList(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(service.findAll(), Quote.class)
                .onErrorResume(throwable -> ServerResponse
                        .badRequest()
                        .body(fromValue(UID_NOT_FOUND_ERROR + throwable.getMessage())));
    }

    public Mono<ServerResponse> getQuote(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(this.service.findOne(id), Quote.class)
                .onErrorResume(throwable -> ServerResponse
                        .badRequest()
                        .body(fromValue(UID_NOT_FOUND_ERROR + throwable.getMessage())));
    }
}
