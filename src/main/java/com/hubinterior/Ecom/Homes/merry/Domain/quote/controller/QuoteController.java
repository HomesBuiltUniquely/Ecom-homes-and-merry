package com.hubinterior.Ecom.Homes.merry.Domain.quote.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.quote.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Origin/Quotes/FullDetails")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("/{quoteId}")
    public ResponseEntity<Object> getQuoteDetails(@PathVariable("quoteId") String quoteId) {
        Object quoteDetails = quoteService.getQuoteFullDetails(quoteId);
        return ResponseEntity.ok(quoteDetails);
    }
}
