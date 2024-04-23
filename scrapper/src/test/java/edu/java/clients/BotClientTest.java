package edu.java.clients;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import dto.LinkUpdateRequest;
import edu.java.backoff.ConstantBackOff;
import edu.java.backoff.ExponentialBackOff;
import edu.java.backoff.LinearBackOff;
import edu.java.configuration.ClientConfig;
import edu.java.configuration.RetryBuilder;
import edu.java.configuration.RetryPolicy;
import java.net.URI;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.resetAllScenarios;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ExponentialBackOff.class, LinearBackOff.class, ConstantBackOff.class, RetryBuilder.class})
@WireMockTest(httpPort = 8080)
class BotClientTest {
    @Autowired
    RetryBuilder retryBuilder;
    @Mock
    private ClientConfig clientConfig;
    @InjectMocks
    private BotClient botClient;

    @BeforeEach
    public void setUp() {
        stubFor(post(urlEqualTo("/updates")).inScenario("Check retry for bot")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(post(urlEqualTo("/updates")).inScenario("Check retry for bot")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );
        stubFor(post(urlEqualTo("/updates")).inScenario("Check retry for bot")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(200)));
    }

    @AfterEach
    public void stop() {
        resetAllScenarios();
    }

    @Test
    void sendUpdateExponentialBlackOff() {
        RetryPolicy retryPolicy = new RetryPolicy();
        retryPolicy.setBackOffType(RetryPolicy.BackOffType.EXPONENTIAL);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);
        ClientConfig.Bot bot = new ClientConfig.Bot("", retryPolicy);
        when(clientConfig.bot()).thenReturn(bot);
        botClient = new BotClient(WebClient.create("http://localhost:8080"), clientConfig, retryBuilder);
        LinkUpdateRequest linkUpdateRequest = new LinkUpdateRequest(
            123L,
            URI.create("http://mycore"),
            "updating link",
            Arrays.asList(1L, 2L, 3L)
        );
        String expectedRequest = "{\"id\": 123, " +
            "\"url\": \"http://mycore\"," +
            "\"description\": \"updating link\"," +
            "\"tgChatIds\": [1, 2, 3]}";

        botClient.sendUpdate(linkUpdateRequest);

        verify(postRequestedFor(urlEqualTo("/updates"))
            .withRequestBody(equalToJson(expectedRequest)));

    }

    @Test
    void sendUpdateLinearBackOff() {
        RetryPolicy retryPolicy = new RetryPolicy();
        retryPolicy.setBackOffType(RetryPolicy.BackOffType.LINEAR);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);
        ClientConfig.Bot bot = new ClientConfig.Bot("", retryPolicy);
        when(clientConfig.bot()).thenReturn(bot);
        botClient = new BotClient(WebClient.create("http://localhost:8080"), clientConfig, retryBuilder);
        LinkUpdateRequest linkUpdateRequest = new LinkUpdateRequest(
            123L,
            URI.create("http://mycore"),
            "updating link",
            Arrays.asList(1L, 2L, 3L)
        );
        String expectedRequest = "{\"id\": 123, " +
            "\"url\": \"http://mycore\"," +
            "\"description\": \"updating link\"," +
            "\"tgChatIds\": [1, 2, 3]}";

        botClient.sendUpdate(linkUpdateRequest);

        verify(postRequestedFor(urlEqualTo("/updates"))
            .withRequestBody(equalToJson(expectedRequest)));

    }

    @Test
    void sendUpdateConstantBlackOff() {
        RetryPolicy retryPolicy = new RetryPolicy();
        retryPolicy.setBackOffType(RetryPolicy.BackOffType.CONSTANT);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);
        ClientConfig.Bot bot = new ClientConfig.Bot("", retryPolicy);
        when(clientConfig.bot()).thenReturn(bot);
        botClient = new BotClient(WebClient.create("http://localhost:8080"), clientConfig, retryBuilder);
        LinkUpdateRequest linkUpdateRequest = new LinkUpdateRequest(
            123L,
            URI.create("http://mycore"),
            "updating link",
            Arrays.asList(1L, 2L, 3L)
        );
        String expectedRequest = "{\"id\": 123, " +
            "\"url\": \"http://mycore\"," +
            "\"description\": \"updating link\"," +
            "\"tgChatIds\": [1, 2, 3]}";

        botClient.sendUpdate(linkUpdateRequest);

        verify(postRequestedFor(urlEqualTo("/updates"))
            .withRequestBody(equalToJson(expectedRequest)));

    }

}
