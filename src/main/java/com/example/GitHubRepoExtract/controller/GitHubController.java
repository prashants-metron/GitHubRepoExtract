package com.example.GitHubRepoExtract.controller;

import java.util.List;
import java.util.Map;

import com.example.GitHubRepoExtract.model.RepositoryDto;
import com.example.GitHubRepoExtract.model.EventDto;
import com.example.GitHubRepoExtract.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // Allow frontend to access
public class GitHubController {

    private static final Logger logger = LoggerFactory.getLogger(GitHubController.class);
    private final GitHubService gitHubService;

    @GetMapping("/repos")
    public List<RepositoryDto> getRepos(@RequestParam String owner,
                                        @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "30") int size) {
        logger.info("Fetching GitHub repositories for user: {}, page: {}, size: {}", owner, page, size);
        return gitHubService.getRepos(owner, page, size);
    }

    @GetMapping("/events")
    public List<EventDto> getEvents(@RequestParam String owner,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "30") int size) {
        logger.info("Fetching GitHub events for user: {}, page: {}, size: {}", owner, page, size);
        return gitHubService.getEvents(owner, page, size);
    }

    @GetMapping("/download-repo")
    public ResponseEntity<byte[]> downloadRepoZip(@RequestParam String owner,
                                                  @RequestParam String repo,
                                                  @RequestParam(defaultValue = "main") String branch) {
        logger.info("Downloading zip for {}/{} branch {}", owner, repo, branch);
        return gitHubService.downloadRepoZip(owner, repo, branch);
    }

    @GetMapping("/commit-activity")
    public List<Map<String, Object>> getCommitActivity(@RequestParam String owner,
                                                       @RequestParam String repo) {
        logger.info("Fetching commit activity for {}/{}", owner, repo);
        return gitHubService.getCommitActivity(owner, repo);
    }

    @GetMapping("/contributors")
    public List<Map<String, Object>> getContributors(@RequestParam String owner,
                                                     @RequestParam String repo,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "30") int size) {
        logger.info("Fetching contributors for {}/{} page: {}, size: {}", owner, repo, page, size);
        return gitHubService.getContributors(owner, repo, page, size);
    }

    @GetMapping("/readme")
    public Map<String, Object> getReadme(@RequestParam String owner,
                                         @RequestParam String repo) {
        logger.info("Fetching README for {}/{}", owner, repo);
        return gitHubService.getReadme(owner, repo);
    }

    @GetMapping("/languages")
    public Map<String, Integer> getLanguages(@RequestParam String owner,
                                             @RequestParam String repo) {
        logger.info("Fetching languages for {}/{}", owner, repo);
        return gitHubService.getLanguages(owner, repo);
    }

    @GetMapping("/starred")
    public List<Map<String, Object>> getStarredRepos(@RequestParam String owner,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "30") int size) {
        logger.info("Fetching starred repos for {}, page: {}, size: {}", owner, page, size);
        return gitHubService.getStarredRepos(owner, page, size);
    }

    @GetMapping("/gists")
    public List<Map<String, Object>> getUserGists(@RequestParam String owner,
                                                  @RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "30") int size) {
        logger.info("Fetching gists for {}, page: {}, size: {}", owner, page, size);
        return gitHubService.getUserGists(owner, page, size);
    }

    @GetMapping("/repo-details")
    public Map<String, Object> getRepoDetails(@RequestParam String owner,
                                              @RequestParam String repo) {
        logger.info("Fetching repo details for {}/{}", owner, repo);
        return gitHubService.getRepoDetails(owner, repo);
    }
}
