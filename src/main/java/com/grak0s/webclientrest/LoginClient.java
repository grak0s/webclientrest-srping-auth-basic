package com.grak0s.webclientrest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class LoginClient {

    private final WebClient client;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public LoginClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8091/oauth")
                .defaultHeaders( header -> header.setBasicAuth("xxxxx", "xxxxx"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
    }

    public Mono<String> getToken() {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "xxxxx");
        formData.add("password", "xxxxxx");
        formData.add("grant_type", "password");

          return this.client.post().uri("/token")
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(Login.class)
                .map(Login::getAccess_token);
    }



}
