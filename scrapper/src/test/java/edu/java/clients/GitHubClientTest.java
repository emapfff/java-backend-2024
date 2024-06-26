package edu.java.clients;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.backoff.ConstantBackOff;
import edu.java.backoff.ExponentialBackOff;
import edu.java.backoff.LinearBackOff;
import edu.java.configuration.ClientConfig;
import edu.java.configuration.RetryBuilder;
import edu.java.configuration.RetryPolicy;
import edu.java.response.GitHubUserResponse;
import edu.java.response.ListBranchesResponse;
import edu.java.response.RepositoryResponse;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import reactor.test.StepVerifier;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.resetAllScenarios;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ExponentialBackOff.class, LinearBackOff.class, ConstantBackOff.class, RetryBuilder.class})
@WireMockTest(httpPort = 8080)
class GitHubClientTest {
    @Autowired
    RetryBuilder retryBuilder;
    @Mock
    private ClientConfig clientConfig;
    @InjectMocks
    private GitHubClient gitHubClient;
    private final RetryPolicy retryPolicy = new RetryPolicy();

    @BeforeEach
    public void setUp() {
        ClientConfig.Github github = new ClientConfig.Github("", retryPolicy);
        when(clientConfig.github()).thenReturn(github);
        gitHubClient = new GitHubClient(WebClient.create("http://localhost:8080"), clientConfig, retryBuilder);

        stubFor(get(urlEqualTo("/users/user")).inScenario("Check retry for user")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(get(urlEqualTo("/users/user")).inScenario("Check retry for user")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );

        stubFor(get(urlEqualTo("/repos/owner/repo")).inScenario("Check retry for repo")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(get(urlEqualTo("/repos/owner/repo")).inScenario("Check retry for repo")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );

        stubFor(get(urlEqualTo("/repos/user/repo/branches")).inScenario("Check retry for branches")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("2")
        );
        stubFor(get(urlEqualTo("/repos/user/repo/branches")).inScenario("Check retry for branches")
            .whenScenarioStateIs("2")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("3")
        );
    }

    @AfterEach
    public void stop() {
        resetAllScenarios();
    }

    @ParameterizedTest
    @EnumSource(RetryPolicy.BackOffType.class)
    void fetchUserTest(RetryPolicy.BackOffType backOffType) {
        stubFor(get(urlEqualTo("/users/user")).inScenario("Check retry for user")
            .whenScenarioStateIs("3")
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"login\" : \"Emil\", " +
                        "\"updated_at\" : \"2024-02-09T17:47:19Z\"}")
            ));
        retryPolicy.setBackOffType(backOffType);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);

        GitHubUserResponse gitHubUserResponse = gitHubClient.fetchUser("user").block();

        assertNotNull(gitHubUserResponse);
        assertEquals(gitHubUserResponse.userName(), "Emil");
        assertEquals(gitHubUserResponse.lastUpdate().toString(), "2024-02-09T17:47:19Z");
    }

    @ParameterizedTest
    @EnumSource(RetryPolicy.BackOffType.class)
    void failFetchUserTest(RetryPolicy.BackOffType backOffType) {
        stubFor(get(urlEqualTo("/users/user")).inScenario("Check retry for user")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("4")
        );
        stubFor(get(urlEqualTo("/users/user")).inScenario("Check retry for user")
            .whenScenarioStateIs("4")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("5")
        );
        stubFor(get(urlEqualTo("/users/user")).inScenario("Check retry for user")
            .whenScenarioStateIs("5")
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"login\" : \"Emil\", " +
                        "\"updated_at\" : \"2024-02-09T17:47:19Z\"}")
            ));
        retryPolicy.setBackOffType(backOffType);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);

        StepVerifier.create(gitHubClient.fetchUser("user")).verifyError();
    }

    @ParameterizedTest
    @EnumSource(RetryPolicy.BackOffType.class)
    void fetchRepositoryTest(RetryPolicy.BackOffType backOffType) {
        stubFor(get(urlEqualTo("/repos/owner/repo")).inScenario("Check retry for repo")
            .whenScenarioStateIs("3")
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-type", "application/json")
                    .withBody("{\"name\" : \"repo_name\", " +
                        "\"updated_at\" : \"2024-02-09T17:47:19Z\", " +
                        "\"owner:login\" : \"user\"}")
            ));
        retryPolicy.setBackOffType(backOffType);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);

        RepositoryResponse repositoryResponse = gitHubClient.fetchRepository("owner", "repo").block();

        assertNotNull(repositoryResponse);
        assertEquals(repositoryResponse.repoName(), "repo_name");
        assertEquals(repositoryResponse.lastUpdate().toString(), "2024-02-09T17:47:19Z");
    }

    @ParameterizedTest
    @EnumSource(RetryPolicy.BackOffType.class)
    void failFetchRepositoryTest(RetryPolicy.BackOffType backOffType) {
        stubFor(get(urlEqualTo("/repos/owner/repo")).inScenario("Check retry for repo")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("4")
        );
        stubFor(get(urlEqualTo("/repos/owner/repo")).inScenario("Check retry for repo")
            .whenScenarioStateIs("4")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("5")
        );
        retryPolicy.setBackOffType(backOffType);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);

        StepVerifier.create(gitHubClient.fetchRepository("owner", "repo")).verifyError();
    }

    @ParameterizedTest
    @EnumSource(RetryPolicy.BackOffType.class)
    void fetchBranchTest(RetryPolicy.BackOffType backOffType) throws IOException {
        String branchesJson = FileUtils.readFileToString(
            new File("src/test/java/edu/java/json/Branches.json"),
            "UTF-8"
        );
        stubFor(get(urlEqualTo("/repos/user/repo/branches")).inScenario("Check retry for branches")
            .whenScenarioStateIs("3")
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(branchesJson)
            )
        );
        retryPolicy.setBackOffType(backOffType);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);

        ListBranchesResponse actualResponse = gitHubClient.fetchBranch("user", "repo").block();

        assertNotNull(actualResponse);
        assertEquals(actualResponse.listBranches().size(), 2);
        assertEquals(actualResponse.listBranches().getFirst().name(), "hw1");
        assertEquals(actualResponse.listBranches().getLast().name(), "hw2");
    }

    @ParameterizedTest
    @EnumSource(RetryPolicy.BackOffType.class)
    void failFetchBranchTest(RetryPolicy.BackOffType backOffType) throws IOException {
        String branchesJson = FileUtils.readFileToString(
            new File("src/test/java/edu/java/json/Branches.json"),
            "UTF-8"
        );
        stubFor(get(urlEqualTo("/repos/user/repo/branches")).inScenario("Check retry for branches")
            .whenScenarioStateIs("3")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("4")
        );
        stubFor(get(urlEqualTo("/repos/user/repo/branches")).inScenario("Check retry for branches")
            .whenScenarioStateIs("4")
            .willReturn(aResponse().withStatus(500))
            .willSetStateTo("5")
        );
        retryPolicy.setBackOffType(backOffType);
        retryPolicy.setMaxAttempts(3);
        retryPolicy.setInitialInterval(2000L);

        StepVerifier.create(gitHubClient.fetchBranch("user", "repo")).verifyError();
    }
}

