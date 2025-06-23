package com.example.GitHubRepoExtract.client;

import java.util.List;

import com.example.GitHubRepoExtract.model.RepositoryDto;
import com.example.GitHubRepoExtract.model.EventDto;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GitHubClient {

    private static final Logger logger = LoggerFactory.getLogger(GitHubClient.class);
    private final WebClient githubWebClient;

    public Mono<List<RepositoryDto>> fetchUserRepos(String username, int page, int size) {
        String url = "/users/" + username + "/repos?page=" + page + "&per_page=" + size;
        logger.info("Fetching GitHub repositories for user: {}, page: {}, size: {}", username, page, size);

        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(RepositoryDto.class)
                .collectList()
                .doOnError(error -> logger.error("Error fetching repos for user {}: {}", username, error.getMessage()));
    }

    public Mono<List<EventDto>> fetchUserEvents(String username, int page, int size) {
        String url = "/users/" + username + "/events?page=" + page + "&per_page=" + size;
        logger.info("Fetching GitHub events for user: {}, page: {}, size: {}", username, page, size);

        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(EventDto.class)
                .collectList()
                .doOnError(error -> logger.error("Error fetching events for user {}: {}", username, error.getMessage()));
    }
}
