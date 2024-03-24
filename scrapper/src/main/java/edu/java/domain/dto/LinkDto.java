package edu.java.domain.dto;

import java.net.URI;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LinkDto {
    Long id;
    URI url;
    OffsetDateTime lastUpdate;
    Integer countBranch;
}
