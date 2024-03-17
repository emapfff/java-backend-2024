package edu.java.domain.dto;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LinkDto {
    Integer id;
    String url;
    OffsetDateTime last_update;
}
