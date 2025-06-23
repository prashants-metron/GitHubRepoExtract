package com.example.GitHubRepoExtract.client;

import com.example.GitHubRepoExtract.model.EventDto;
import com.example.GitHubRepoExtract.model.RepositoryDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GitHubClient {

    private static final Logger logger = LoggerFactory.getLogger(GitHubClient.class);
    private final WebClient githubWebClient;

    public Mono<List<RepositoryDto>> fetchUserRepos(String username, int page, int size) {
        String url = "/users/" + username + "/repos?page=" + page + "&per_page=" + size;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(RepositoryDto.class)
                .collectList()
                .doOnError(e -> logger.error("Error fetching repos: {}", e.getMessage()));
    }

    public Mono<List<EventDto>> fetchUserEvents(String username, int page, int size) {
        String url = "/users/" + username + "/events?page=" + page + "&per_page=" + size;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(EventDto.class)
                .collectList()
                .doOnError(e -> logger.error("Error fetching events: {}", e.getMessage()));
    }

    public Mono<ResponseEntity<byte[]>> downloadRepoZip(String owner, String repo, String branch) {
        String url = "/repos/" + owner + "/" + repo + "/zipball/" + branch;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .toEntity(byte[].class)
                .doOnError(e -> logger.error("Error downloading ZIP: {}", e.getMessage()));
    }

    public Mono<List<Map<String, Object>>> fetchCommitActivity(String owner, String repo) {
        String url = "/repos/" + owner + "/" + repo + "/stats/commit_activity";
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .doOnError(e -> logger.error("Error fetching commit activity: {}", e.getMessage()));
    }

    public Mono<List<Map<String, Object>>> fetchContributors(String owner, String repo, int page, int size) {
        String url = "/repos/" + owner + "/" + repo + "/contributors?page=" + page + "&per_page=" + size;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .doOnError(e -> logger.error("Error fetching contributors: {}", e.getMessage()));
    }

    public Mono<Map<String, Object>> fetchReadme(String owner, String repo) {
        String url = "/repos/" + owner + "/" + repo + "/readme";
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .doOnError(e -> logger.error("Error fetching readme: {}", e.getMessage()));
    }

    public Mono<Map<String, Integer>> fetchLanguages(String owner, String repo) {
        String url = "/repos/" + owner + "/" + repo + "/languages";
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Integer>>() {})
                .doOnError(e -> logger.error("Error fetching languages: {}", e.getMessage()));
    }

    public Mono<List<Map<String, Object>>> fetchStarredRepos(String username, int page, int size) {
        String url = "/users/" + username + "/starred?page=" + page + "&per_page=" + size;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .doOnError(e -> logger.error("Error fetching starred repos: {}", e.getMessage()));
    }

    public Mono<List<Map<String, Object>>> fetchUserGists(String username, int page, int size) {
        String url = "/users/" + username + "/gists?page=" + page + "&per_page=" + size;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .doOnError(e -> logger.error("Error fetching gists: {}", e.getMessage()));
    }

    public Mono<Map<String, Object>> fetchRepoDetails(String owner, String repo) {
        String url = "/repos/" + owner + "/" + repo;
        return githubWebClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .doOnError(e -> logger.error("Error fetching repo details: {}", e.getMessage()));
    }

}
