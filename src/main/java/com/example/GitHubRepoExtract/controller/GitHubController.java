package com.example.GitHubRepoExtract.controller;

import java.util.List;
import com.example.GitHubRepoExtract.model.RepositoryDto;
import com.example.GitHubRepoExtract.model.EventDto;
import com.example.GitHubRepoExtract.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // Allow frontend to access
public class GitHubController {

    private static final Logger logger = LoggerFactory.getLogger(GitHubController.class);

    private final GitHubService gitHubService;

    @GetMapping("/repos")
    public List<RepositoryDto> getRepos(@RequestParam(required = false) String username,
                                        @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "30") int size) {

        logger.info("Fetching GitHub repositories for user: {}, page: {}, size: {}", username, page, size);

        List<RepositoryDto> repos = gitHubService.getRepos(username, page, size);

        logger.debug("Fetched {} repositories for user {}", repos.size(), username);
        return repos;
    }

    @GetMapping("/events")
    public List<EventDto> getEvents(@RequestParam(required = false) String username,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "30") int size) {

        logger.info("Fetching GitHub events for user: {}, page: {}, size: {}", username, page, size);

        List<EventDto> events = gitHubService.getEvents(username, page, size);

        logger.debug("Fetched {} events for user {}", events.size(), username);
        return events;
    }
}
