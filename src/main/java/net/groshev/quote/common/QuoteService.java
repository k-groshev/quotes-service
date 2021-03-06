package net.groshev.quote.common;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class QuoteService {
    @Value("${QUOTE_SERVICE:backToFuture}")
    private String quoteConfig;

    private final Faker faker;

    public QuoteService() {
        faker = Faker.instance();
    }

    public String getFakeQuote() {
        final String quote;

        switch (quoteConfig.toUpperCase()) {
            case "CHUCK":
                quote = faker.chuckNorris().fact();
                break;
            case "HOBBIT":
                quote = faker.hobbit().quote();
                break;
            default:
                quote = faker.backToTheFuture().quote();
        }
        return quote;
    }


    public Mono<Quote> findOne(String id) {
        return Mono.justOrEmpty(new Quote(getFakeQuote()));
    }

    public Flux<Quote> findAll() {
        return Flux.just(new Quote(getFakeQuote()), new Quote(getFakeQuote()), new Quote(getFakeQuote()));
    }
}
