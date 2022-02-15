package net.groshev.quote.rest;

import net.groshev.quote.common.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuoteEndpoint {

    private final QuoteService quoteService;

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> hello() {
        return new ResponseEntity<>(new Response(quoteService.getFakeQuote()), HttpStatus.OK);
    }
}
