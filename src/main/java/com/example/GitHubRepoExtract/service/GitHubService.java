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

    public List<RepositoryDto> getRepos(String username) {
        return githubClient.fetchUserRepos(username).block();
    }

    public List<EventDto> getEvents(String username) {
        return githubClient.fetchUserEvents(username).block();
    }
}