package com.hubinterior.Ecom.Homes.merry.Domain.quote.service;

import com.hubinterior.Ecom.Homes.merry.Domain.quote.client.ProlanceApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuoteService {

    private final ProlanceApiClient prolanceApiClient;

    public Object getQuoteFullDetails(String quoteId) {
        String sessionId = prolanceApiClient.authenticatePartnerSession();
        return prolanceApiClient.fetchQuoteFullDetails(quoteId, sessionId);
    }
}
