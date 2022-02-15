package net.groshev.quote.api;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class QuotesRouter {

    @Bean
    public RouterFunction<ServerResponse> journalsRoute(QuotesHandler quotesHandler) {
        RouterFunction<ServerResponse> routes = RouterFunctions
                .route(RequestPredicates
                                .GET("/")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        quotesHandler::getQuotesList)
                .andRoute(RequestPredicates.GET("/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        quotesHandler::getQuote);
        return RouterFunctions.nest(RequestPredicates.path("/api/v1/quotes"), routes);
    }
}
