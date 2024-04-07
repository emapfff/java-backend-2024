package edu.java.service;

import dto.LinkUpdateRequest;
import edu.java.clients.BotClient;
import edu.java.domain.LinkRepository;
import edu.java.domain.dto.LinkDto;
import edu.java.tools.LinkParser;
import edu.java.tools.Urls;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinkUpdaterServiceTest {
    @Mock
    private BotClient botClient;
    @Mock
    private LinkRepository linkRepository;
    @Mock
    private GithubUpdater githubUpdater;
    @Mock
    private StackOverflowUpdater stackOverflowUpdater;
    @Mock
    private LinkParser linkParse;
    @InjectMocks
    private LinkUpdaterService linkUpdaterService;

    @Test
    void githubUpdateTest() {
        LinkDto link = new LinkDto(1L, URI.create("https://github.com/example/repo"), OffsetDateTime.now());
        List<LinkDto> links = new ArrayList<>();
        links.add(link);
        when(linkRepository.findAll()).thenReturn(links);
        when(linkParse.parse(link.url())).thenReturn(Urls.GITHUB);
        when(githubUpdater.update(link)).thenReturn(true);

        linkUpdaterService.checkUpdates();

        verify(botClient).sendUpdate(any(LinkUpdateRequest.class));
    }

    @Test
    void githubCheckBranchesTest() {
        LinkDto link = new LinkDto(1L, URI.create("https://github.com/example/repo"), OffsetDateTime.now());
        List<LinkDto> links = new ArrayList<>();
        links.add(link);
        when(linkRepository.findAll()).thenReturn(links);
        when(linkParse.parse(link.url())).thenReturn(Urls.GITHUB);
        when(githubUpdater.update(link)).thenReturn(false);
        when(githubUpdater.checkBranches(link)).thenReturn(true);

        linkUpdaterService.checkUpdates();

        verify(botClient).sendUpdate(any(LinkUpdateRequest.class));
    }

    @Test
    void githubNotUpdatesTest() {
        LinkDto link = new LinkDto(1L, URI.create("https://github.com/example/repo"), OffsetDateTime.now());
        List<LinkDto> links = new ArrayList<>();
        links.add(link);
        when(linkRepository.findAll()).thenReturn(links);
        when(linkParse.parse(link.url())).thenReturn(Urls.GITHUB);
        when(githubUpdater.update(link)).thenReturn(false);
        when(githubUpdater.checkBranches(link)).thenReturn(false);

        linkUpdaterService.checkUpdates();

        verifyNoInteractions(botClient);
    }

    @Test
    void stackOverflowUpdateTest() {
        LinkDto link = new LinkDto(1L, URI.create("https://stackoverflow"), OffsetDateTime.now());
        List<LinkDto> links = new ArrayList<>();
        links.add(link);
        when(linkRepository.findAll()).thenReturn(links);
        when(linkParse.parse(link.url())).thenReturn(Urls.STACKOVERFLOW);
        when(stackOverflowUpdater.update(link)).thenReturn(true);

        linkUpdaterService.checkUpdates();

        verify(botClient).sendUpdate(any(LinkUpdateRequest.class));
    }

    @Test
    void stackOverflowCheckAnswersTest() {
        LinkDto link = new LinkDto(1L, URI.create("https://stackoverflow"), OffsetDateTime.now());
        List<LinkDto> links = new ArrayList<>();
        links.add(link);
        when(linkRepository.findAll()).thenReturn(links);
        when(linkParse.parse(link.url())).thenReturn(Urls.STACKOVERFLOW);
        when(stackOverflowUpdater.update(link)).thenReturn(false);
        when(stackOverflowUpdater.checkAnswers(link)).thenReturn(true);

        linkUpdaterService.checkUpdates();

        verify(botClient).sendUpdate(any(LinkUpdateRequest.class));
    }

    @Test
    void stackOverflowNotUpdatesTest() {
        LinkDto link = new LinkDto(1L, URI.create("https://stackoverflow"), OffsetDateTime.now());
        List<LinkDto> links = new ArrayList<>();
        links.add(link);
        when(linkRepository.findAll()).thenReturn(links);
        when(linkParse.parse(link.url())).thenReturn(Urls.STACKOVERFLOW);
        when(stackOverflowUpdater.update(link)).thenReturn(false);
        when(stackOverflowUpdater.checkAnswers(link)).thenReturn(false);

        linkUpdaterService.checkUpdates();

        verifyNoInteractions(botClient);
    }



}
