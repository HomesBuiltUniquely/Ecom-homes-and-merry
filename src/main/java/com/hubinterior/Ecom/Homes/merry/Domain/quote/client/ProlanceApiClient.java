package com.hubinterior.Ecom.Homes.merry.Domain.quote.client;

import com.hubinterior.Ecom.Homes.merry.Domain.quote.dto.ProlanceLoginResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.quote.dto.ProlanceTokenResponse;
import com.hubinterior.Ecom.Homes.merry.Exception.BusinessRuleException;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import com.hubinterior.Ecom.Homes.merry.Exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class ProlanceApiClient {

    private final RestClient restClient;
    private final String apiKey;
    private final String username;
    private final String password;

    public ProlanceApiClient(
            @Value("${prolance.api.baseUrl:https://api.prolance.design}") String baseUrl,
            @Value("${prolance.api.apiKey:c4b24d7c25cc11f1a660066e82f6587b}") String apiKey,
            @Value("${prolance.api.username:}") String username,
            @Value("${prolance.api.password:}") String password) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
        this.apiKey = apiKey;
        this.username = username;
        this.password = password;
    }

    public String obtainOAuthToken() {
        try {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("grant_type", "password");
            formData.add("username", username);
            formData.add("password", password);

            ProlanceTokenResponse tokenResponse = restClient.post()
                    .uri("/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(ProlanceTokenResponse.class);

            if (tokenResponse != null && tokenResponse.accessToken() != null && !tokenResponse.accessToken().isBlank()) {
                log.info("Successfully obtained Prolance OAuth access token.");
                return tokenResponse.accessToken();
            }
            throw new UnauthorizedException("Prolance OAuth /token API returned empty access_token.");
        } catch (HttpClientErrorException ex) {
            log.error("Prolance OAuth HTTP error: {}", ex.getStatusCode(), ex);
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED || ex.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new UnauthorizedException("Invalid Prolance OAuth credentials or API key.");
            }
            throw new BusinessRuleException("Prolance OAuth token request failed: " + ex.getMessage());
        } catch (Exception e) {
            log.error("Failed to obtain Prolance OAuth token", e);
            throw new BusinessRuleException("Prolance OAuth authentication failed: " + e.getMessage());
        }
    }

    public String authenticatePartnerSession() {
        String accessToken = obtainOAuthToken();
        try {
            ProlanceLoginResponse response = restClient.post()
                    .uri("/Origin/Partners/LoginAPI")
                    .header("OriginAPIKey", apiKey)
                    .header("NoEncryption", "1")
                    .header("Authorization", "Bearer " + accessToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(ProlanceLoginResponse.class);

            if (response != null && Boolean.TRUE.equals(response.status()) && response.data() != null && !response.data().isEmpty()) {
                String sessionId = response.data().get(0).sessionId();
                log.info("Successfully authenticated with Prolance Partner LoginAPI. SessionId: {}", sessionId);
                return sessionId;
            }
            throw new BusinessRuleException("Prolance Partner Login API returned unsuccessful status.");
        } catch (HttpClientErrorException ex) {
            log.error("Prolance Partner Login HTTP error: {}", ex.getStatusCode(), ex);
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED || ex.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new UnauthorizedException("Unauthorized access to Prolance Partner LoginAPI.");
            }
            throw new BusinessRuleException("Prolance Partner LoginAPI failed: " + ex.getMessage());
        } catch (Exception e) {
            log.error("Failed to authenticate with Prolance Partner LoginAPI", e);
            throw new BusinessRuleException("Prolance partner session login failed: " + e.getMessage());
        }
    }

    public <T> T fetchQuoteFullDetails(String quoteId, String sessionId, Class<T> responseType) {
        try {
            return restClient.get()
                    .uri("/Origin/Quotes/FullDetails/{quoteId}", quoteId)
                    .header("OriginSessionID", sessionId)
                    .header("OriginAPIKey", apiKey)
                    .header("NoEncryption", "1")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(responseType);
        } catch (HttpClientErrorException ex) {
            log.error("Prolance Quote Details HTTP error for quoteId {}: {}", quoteId, ex.getStatusCode(), ex);
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException("Prolance Quote with ID '" + quoteId + "' was not found.");
            }
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED || ex.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new UnauthorizedException("Unauthorized access or expired session for Prolance Quote API.");
            }
            throw new BusinessRuleException("Failed to fetch Prolance quote details: " + ex.getMessage());
        } catch (Exception e) {
            log.error("Failed to fetch Prolance quote details for quoteId: {}", quoteId, e);
            throw new BusinessRuleException("Failed to fetch Prolance quote details: " + e.getMessage());
        }
    }

    public Object fetchQuoteFullDetails(String quoteId, String sessionId) {
        return fetchQuoteFullDetails(quoteId, sessionId, Object.class);
    }
}
