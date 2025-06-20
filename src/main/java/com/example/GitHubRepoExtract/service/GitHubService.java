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

    public List<RepositoryDto> getRepos() {
        return githubClient.fetchUserRepos().block();
    }

    public List<EventDto> getEvents() {
        return githubClient.fetchUserEvents().block();
    }
}