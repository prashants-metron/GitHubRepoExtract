package com.example.GitHubRepoExtract.client;

import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.example.GitHubRepoExtract.model.RepositoryDto;
import com.example.GitHubRepoExtract.model.EventDto;


@Component
@RequiredArgsConstructor

public class GitHubClient {

    private final WebClient githubWebClient;

    @Value("${github.username}")
    private String username;

    public Mono<List<RepositoryDto>> fetchUserRepos(String username, int page, int size) {
        String url = "/users/" + username + "/repos?page=" + page + "&per_page=" + size;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(RepositoryDto.class)
                .collectList();
    }

    public Mono<List<EventDto>> fetchUserEvents(String username, int page, int size) {
        String url = "/users/" + username + "/repos?page=" + page + "&per_page=" + size;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(EventDto.class)
                .collectList();
    }
}