package net.groshev.quote.common;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class QuoteService {
    @Value("${QUOTE_SERVICE:backToFuture}")
    private String quoteConfig;

    private final Faker faker;

    public QuoteService() {
        faker = Faker.instance();
    }

    public String getFakeQuote() {
        switch (quoteConfig.toUpperCase()) {
            case "CHUCK" -> faker.chuckNorris().fact();
            case "HOBBIT"-> faker.hobbit().quote();
        }
        return faker.backToTheFuture().quote();
    }


    public Mono<Quote> findOne(String id) {
        return Mono.justOrEmpty(new Quote(getFakeQuote()));
    }

    public Flux<Quote> findAll() {
        return Flux.just(new Quote(getFakeQuote()), new Quote(getFakeQuote()), new Quote(getFakeQuote()));
    }
}
