package com.example.GitHubRepoExtract.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {
    private String id;
    private String type;
    private String created_at;
    private Actor actor;
    private Repo repo;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Actor {
        private String login;
        private String avatar_url;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Repo {
        private String name;
        private String url;
    }
}
