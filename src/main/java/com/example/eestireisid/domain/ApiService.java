package com.example.eestireisid.domain;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient webClient;

    private final String USERNAME = "Peeter";
    private final String PASSWORD = "b1833c2fe832af51ba3c8f494e7997e2";


    public ApiService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://assignments.novater.com")
                .build();
    }

    public Mono<JsonNode> getTimetableFromApi() {
        return webClient.get()
                .uri("/v1/bus/schedule")
                .headers(headers -> headers.setBasicAuth(USERNAME, PASSWORD))
                .retrieve()
                .bodyToMono(JsonNode.class);


    }
}
