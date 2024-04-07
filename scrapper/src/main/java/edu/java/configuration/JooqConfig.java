package edu.java.configuration;

import edu.java.domain.jooq.JooqChatRepository;
import edu.java.domain.jooq.JooqConsistsRepository;
import edu.java.domain.jooq.JooqGithubLinkRepository;
import edu.java.domain.jooq.JooqLinkRepository;
import edu.java.domain.jooq.JooqStackOverflowLinkRepository;
import org.jooq.DSLContext;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class JooqConfig {
    @Bean
    public DefaultConfigurationCustomizer postgresJooqCustomizer() {
        return (DefaultConfiguration c) -> c.settings()
            .withRenderSchema(false)
            .withRenderFormatted(true)
            .withRenderQuotedNames(RenderQuotedNames.NEVER);
    }

    @Bean
    @Primary
    public JooqChatRepository jooqChatRepository(DSLContext dslContext) {
        return new JooqChatRepository(dslContext);
    }

    @Bean
    @Primary
    public JooqLinkRepository jooqLinkRepository(
        JooqConsistsRepository jooqConsistsRepository,
        JooqChatRepository jooqChatRepository,
        DSLContext dslContext
    ) {
        return new JooqLinkRepository(dslContext, jooqConsistsRepository, jooqChatRepository);
    }

    @Bean
    @Primary
    public JooqConsistsRepository jooqConsistsRepository(DSLContext dslContext) {
        return new JooqConsistsRepository(dslContext);
    }

    @Bean
    @Primary
    public JooqGithubLinkRepository jooqGithubLinkRepository(
        DSLContext dslContext,
        JooqLinkRepository jooqLinkRepository
    ) {
        return new JooqGithubLinkRepository(dslContext, jooqLinkRepository);
    }

    @Bean
    @Primary
    public JooqStackOverflowLinkRepository jooqStackOverflowLinkRepository(
        DSLContext dslContext,
        JooqLinkRepository jooqLinkRepository
    ) {
        return new JooqStackOverflowLinkRepository(dslContext, jooqLinkRepository);
    }
}