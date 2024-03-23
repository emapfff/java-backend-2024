package edu.java.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RepositoryResponse(
    @JsonProperty("name") String repoName,
    @JsonProperty("updated_at") OffsetDateTime lastUpdate
) {}
