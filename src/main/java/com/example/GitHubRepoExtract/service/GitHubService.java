package com.example.GitHubRepoExtract.service;

import java.util.*;
import com.example.GitHubRepoExtract.client.GitHubClient;
import com.example.GitHubRepoExtract.model.RepositoryDto;
import com.example.GitHubRepoExtract.model.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GitHubService {
    private final GitHubClient githubClient;

    public List<RepositoryDto> getRepos(String username, int page, int size) {
        return githubClient.fetchUserRepos(username, page, size).block();
    }

    public List<EventDto> getEvents(String username, int page, int size) {
        return githubClient.fetchUserEvents(username, page, size).block();
    }

    public ResponseEntity<byte[]> downloadRepoZip(String owner, String repo, String branch) {
        return githubClient.downloadRepoZip(owner, repo, branch).block();
    }

    public List<Map<String, Object>> getCommitActivity(String owner, String repo) {
        return githubClient.fetchCommitActivity(owner, repo).block();
    }

    public List<Map<String, Object>> getContributors(String owner, String repo, int page, int size) {
        return githubClient.fetchContributors(owner, repo, page, size).block();
    }

    public Map<String, Object> getReadme(String owner, String repo) {
        return githubClient.fetchReadme(owner, repo).block();
    }

    public Map<String, Integer> getLanguages(String owner, String repo) {
        return githubClient.fetchLanguages(owner, repo).block();
    }

    public List<Map<String, Object>> getStarredRepos(String username, int page, int size) {
        return githubClient.fetchStarredRepos(username, page, size).block();
    }

    public List<Map<String, Object>> getUserGists(String username, int page, int size) {
        return githubClient.fetchUserGists(username, page, size).block();
    }

    public Map<String, Object> getRepoDetails(String owner, String repo) {
        return githubClient.fetchRepoDetails(owner, repo).block();
    }
}
