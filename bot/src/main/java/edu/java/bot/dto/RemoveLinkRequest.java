package edu.java.bot.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.net.URI;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record RemoveLinkRequest(
    URI link
) {}
