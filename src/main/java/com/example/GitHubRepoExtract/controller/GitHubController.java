package com.example.GitHubRepoExtract.controller;
import java.util.*;
import com.example.GitHubRepoExtract.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.GitHubRepoExtract.model.RepositoryDto;
import com.example.GitHubRepoExtract.model.EventDto;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // Allow frontend to access
public class GitHubController {

    private final GitHubService gitHubService;

    @GetMapping("/repos")
    public List<RepositoryDto> getRepos(@RequestParam(required = false) String username) {
        return gitHubService.getRepos(username);
    }

    @GetMapping("/events")
    public List<EventDto>  getEvents(@RequestParam(required = false) String username) {
        return gitHubService.getEvents(username);
    }
}
