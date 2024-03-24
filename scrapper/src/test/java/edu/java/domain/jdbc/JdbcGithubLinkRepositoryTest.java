package edu.java.domain.jdbc;

import edu.java.domain.dto.GithubLinkDto;
import edu.java.domain.dto.LinkDto;
import edu.java.scrapper.IntegrationTest;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JdbcGithubLinkRepositoryTest extends IntegrationTest {
    @Autowired
    private JdbcGithubLinkRepository githubLinkRepository;

    @Autowired
    private JdbcLinkRepository linkRepository;
    @Autowired
    private JdbcChatRepository chatRepository;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        chatRepository.add(1234L);
        chatRepository.add(123L);
        linkRepository.add(1234L, URI.create("http://github"), OffsetDateTime.now());
        linkRepository.add(1234L, URI.create("http://stackoverflow"), OffsetDateTime.now());
        linkRepository.add(123L, URI.create("http://github"), OffsetDateTime.now());

        githubLinkRepository.add(1234L, URI.create("http://github"), 5);

        List<GithubLinkDto> githubLinks = githubLinkRepository.findAll();
        LinkDto linkDto = linkRepository.findLinkByChatIdAndUrl(1234L, URI.create("http://github"));
        assertEquals(githubLinks.getLast().getLinkId(), linkDto.getId());
        assertEquals(githubLinks.getLast().getCountBranches(), 5);
    }

    @Test
    @Transactional
    @Rollback
    void findAllByTgChatIdTest() {
        chatRepository.add(1234L);
        chatRepository.add(123L);
        linkRepository.add(1234L, URI.create("http://github"), OffsetDateTime.now());
        linkRepository.add(1234L, URI.create("http://stackoverflow"), OffsetDateTime.now());
        linkRepository.add(123L, URI.create("http://github"), OffsetDateTime.now());
        githubLinkRepository.add(1234L, URI.create("http://github"), 5);
        githubLinkRepository.add(123L, URI.create("http://github"), 10);

        List<GithubLinkDto> githubLinks = githubLinkRepository.findAllByTgChatIdAndUrl(1234L, URI.create("http://github"));

        assertEquals(1, githubLinks.size());
        assertEquals(5, githubLinks.getLast().getCountBranches());
    }

    @Test
    @Transactional
    @Rollback
    void findGithubLinkByLinkIdTest() {
        chatRepository.add(1234L);
        chatRepository.add(123L);
        linkRepository.add(1234L, URI.create("http://github"), OffsetDateTime.now());
        linkRepository.add(1234L, URI.create("http://stackoverflow"), OffsetDateTime.now());
        linkRepository.add(123L, URI.create("http://github"), OffsetDateTime.now());
        githubLinkRepository.add(1234L, URI.create("http://github"), 5);
        githubLinkRepository.add(123L, URI.create("http://github"), 10);
        Long linkId = linkRepository.findLinkByChatIdAndUrl(1234L, URI.create("http://github")).getId();
        GithubLinkDto githubLinkDto = githubLinkRepository.findGithubLinkByLinkId(linkId);

        assertEquals(githubLinkDto.getCountBranches(), 5);
        assertEquals(githubLinkDto.getLinkId(), linkId);
    }
    @Test
    @Transactional
    @Rollback
    void checkRemove() {
        chatRepository.add(1234L);
        chatRepository.add(123L);
        linkRepository.add(1234L, URI.create("http://github"), OffsetDateTime.now());
        linkRepository.add(1234L, URI.create("http://stackoverflow"), OffsetDateTime.now());
        linkRepository.add(123L, URI.create("http://github"), OffsetDateTime.now());
        githubLinkRepository.add(1234L, URI.create("http://github"), 5);
        githubLinkRepository.add(123L, URI.create("http://github"), 10);

        linkRepository.remove(1234L, URI.create("http://github"));

        List<GithubLinkDto> githubLinks = githubLinkRepository.findAllByTgChatIdAndUrl(1234L, URI.create("http://github"));

        assertEquals(githubLinks.size(), 0);
    }
}