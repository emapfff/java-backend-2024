package edu.java.domain.repository;

import edu.java.domain.dto.LinkDto;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class JdbcLinkRepository {
    private static final String JOIN_TABLES =
        "SELECT link.id, link.url, link.last_update "
            + "FROM link JOIN consists ON link.id = consists.link_id "
            + "JOIN chat ON chat.id = consists.chat_id ";

    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void add(Long tgChatId, URI url, OffsetDateTime lastUpdate) {
        jdbcTemplate.update(
            "INSERT INTO link (url, last_update) VALUES (?, ?)",
            url.toString(),
            Timestamp.valueOf(lastUpdate.toLocalDateTime())
        );
        Long chatId = findIdByTgChatId(tgChatId);
        Long linkId = findAllTuplesByUrl(url).getLast().getId();
        jdbcTemplate.update(
            "INSERT INTO consists (chat_id, link_id) VALUES (?, ?)",
            chatId,
            linkId
        );
    }

    @Transactional
    public List<LinkDto> findAllTuplesByUrl(URI url) {
        return jdbcTemplate.query(
            "SELECT * FROM link WHERE url=?",
            (rs, rowNum) -> getLinkDto(rs),
            url.toString()
        );
    }

    @Transactional
    public Long findIdByTgChatId(Long tgChatId) {
        return jdbcTemplate.queryForObject(
            "SELECT id FROM chat WHERE tg_chat_id=?",
            Long.class,
            tgChatId
        );

    }

    @Transactional
    public void remove(Long tgChatId, URI url) {
        Long linkId = findLinkIdByChatIdAndUrl(tgChatId, url).getId();
        jdbcTemplate.update(
            "DELETE FROM link WHERE url=? and id=?",
            url.toString(),
            linkId
        );
    }

    @Transactional
    public LinkDto findLinkIdByChatIdAndUrl(Long tgChatId, URI url) {
        return jdbcTemplate.queryForObject(
            JOIN_TABLES + " WHERE chat.tg_chat_id = ? AND url=?",
            (rs, rowNum) -> getLinkDto(rs),
            tgChatId,
            url.toString()
        );
    }

    @Transactional
    public List<Long> findAllTgChatIdsByUrl(URI url) {
        return jdbcTemplate.queryForList(
            """
                SELECT tg_chat_id
                FROM link
                JOIN consists ON link.id = consists.link_id
                JOIN chat ON chat.id = consists.chat_id
                WHERE link.url=?""",
            Long.class,
            url.toString()
        );
    }

    @Transactional
    public List<LinkDto> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM link",
            (rs, rowNum) -> getLinkDto(rs)
        );
    }

    @Transactional
    public List<LinkDto> findAllByTgChatId(Long tgChatId) {
        return jdbcTemplate.query(
            JOIN_TABLES + " WHERE chat.tg_chat_id = ?",
            (rs, rowNum) -> getLinkDto(rs),
            tgChatId
        );
    }

    @NotNull
    private LinkDto getLinkDto(ResultSet rs) throws SQLException {
        LinkDto linkDto = new LinkDto();
        linkDto.setId(rs.getLong("id"));
        linkDto.setUrl(URI.create(rs.getString("url")));
        LocalDateTime localDateTime = rs.getTimestamp("last_update").toLocalDateTime();
        ZoneOffset systemZoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
        linkDto.setLastUpdate((localDateTime.atOffset(systemZoneOffset)).truncatedTo(ChronoUnit.SECONDS));
        return linkDto;
    }
}
