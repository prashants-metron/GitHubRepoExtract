package com.example.GitHubRepoExtract.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryDto {
    private String name;
    private String full_name;
    private String html_url;
    private String description;
    private boolean fork;
    private int stargazers_count;
    private int forks_count;
}
