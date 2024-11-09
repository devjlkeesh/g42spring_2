package dev.jlkeesh.module9;

import dev.jlkeesh.module9.configuration.security.UserSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
@EnableScheduling
/**
 * DDD-> Domain Driver Design
 */
public class Module9Application {

    public static void main(String[] args) {
        SpringApplication.run(Module9Application.class, args);
    }

    @Bean
    public AuditorAware<Long> auditorProvider(UserSession userSession, DataSource dataSource) {
        return () -> Optional.of(userSession.requireUserId());
    }

}
