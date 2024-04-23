package edu.java.bot.clients;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import dto.AddLinkRequest;
import dto.RemoveLinkRequest;
import edu.java.bot.backoff.ConstantBackOff;
import edu.java.bot.backoff.ExponentialBackOff;
import edu.java.bot.backoff.LinearBackOff;
import edu.java.bot.configuration.ClientConfig;
import edu.java.bot.configuration.RetryBuilder;
import edu.java.bot.configuration.RetryPolicy;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static org.mockito.Mockito.when;

@WireMockTest(httpPort = 8080)
@SpringBootTest(classes = {ExponentialBackOff.class, LinearBackOff.class, ConstantBackOff.class, RetryBuilder.class})
class ScrapperClientTest {
    @Autowired
    RetryBuilder retryBuilder;
    @Mock
    private ClientConfig clientConfig;
    @InjectMocks
    private ScrapperClient scrapperClient;

    @BeforeEach
    public void setUp() {
        RetryPolicy retryPolicy = new RetryPolicy();
        retryPolicy.setBackOffType(RetryPolicy.BackOffType.EXPONENTIAL);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);
        ClientConfig.Path path = new ClientConfig.Path("/links", "/tg-chat/");
        ClientConfig.Header header = new ClientConfig.Header("Tg-Chat-Id");
        ClientConfig.Scrapper scrapper = new ClientConfig.Scrapper("", path, header, retryPolicy);
        when(clientConfig.scrapper()).thenReturn(scrapper);

        scrapperClient = new ScrapperClient(WebClient.create("http://localhost:8080"), clientConfig, retryBuilder);
    }

    @Test
    void registrationChat() {
        stubFor(post(urlEqualTo("/tg-chat/123")).inScenario("registration")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(post(urlEqualTo("/tg-chat/123")).inScenario("registration")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );
        stubFor(post(urlEqualTo("/tg-chat/123")).inScenario("registration")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(200))
        );

        StepVerifier.create(scrapperClient.registrationChat(123L)).verifyComplete();

        verify(postRequestedFor(urlEqualTo("/tg-chat/123")));
    }

    @Test
    void removeChat() {
        stubFor(post(urlEqualTo("/tg-chat/123")).inScenario("removing")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(post(urlEqualTo("/tg-chat/123")).inScenario("removing")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );
        stubFor(post(urlEqualTo("/tg-chat/123")).inScenario("removing")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(200))
        );

        StepVerifier.create(scrapperClient.removeChat(123L)).verifyComplete();

        verify(postRequestedFor(urlEqualTo("/tg-chat/123")));
    }

    @Test
    void getLinks() {
        stubFor(get(urlEqualTo("/links")).inScenario("get")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(get(urlEqualTo("/links")).inScenario("get")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );
        stubFor(get(urlEqualTo("/links")).inScenario("get")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(200)));

        StepVerifier.create(scrapperClient.getLinks(123L)).verifyComplete();

        verify(getRequestedFor(urlEqualTo("/links"))
            .withHeader("Tg-Chat-Id", equalTo(String.valueOf(123)))
        );
    }

    @Test
    void addLink() {
        stubFor(post(urlEqualTo("/links")).inScenario("post")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(post(urlEqualTo("/links")).inScenario("post")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );
        stubFor(post(urlEqualTo("/links")).inScenario("post")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(200)));
        AddLinkRequest addLinkRequest = new AddLinkRequest(URI.create("http://mycore"));
        String expectedRequest = "{ \"link\" : \"http://mycore\"}";

        StepVerifier.create(scrapperClient.addLink(123L, addLinkRequest)).verifyComplete();

        verify(postRequestedFor(urlEqualTo("/links"))
            .withHeader("Tg-Chat-Id", equalTo(String.valueOf(123)))
            .withRequestBody(equalToJson(expectedRequest))
        );
    }

    @Test
    void deleteLink() {
        stubFor(delete(urlEqualTo("/links")).inScenario("delete")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(delete(urlEqualTo("/links")).inScenario("delete")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );
        stubFor(delete(urlEqualTo("/links")).inScenario("delete")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(200)));
        RemoveLinkRequest removeLinkRequest = new RemoveLinkRequest(URI.create("http://mycore"));
        String expectedRequest = "{ \"link\" : \"http://mycore\"}";

        StepVerifier.create(scrapperClient.deleteLink(123L, removeLinkRequest)).verifyComplete();

        verify(deleteRequestedFor(urlEqualTo("/links"))
            .withHeader("Tg-Chat-Id", equalTo(String.valueOf(123)))
            .withRequestBody(equalToJson(expectedRequest))
        );
    }
}
