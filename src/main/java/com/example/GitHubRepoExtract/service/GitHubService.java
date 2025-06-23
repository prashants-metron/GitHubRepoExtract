package com.example.GitHubRepoExtract.service;
import java.util.*;
import com.example.GitHubRepoExtract.client.GitHubClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.GitHubRepoExtract.model.RepositoryDto;
import com.example.GitHubRepoExtract.model.EventDto;


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
}