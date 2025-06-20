package com.example.GitHubRepoExtract.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${github.token}")
    private String githubToken;

    @Bean
    public WebClient githubWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader("Authorization", "Bearer " + githubToken)
                .build();
    }
}