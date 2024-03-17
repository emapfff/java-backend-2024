package edu.java.domain.dto;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChatDto {
    private Integer id;
    private String userName;
    private OffsetDateTime createdAt;
}
