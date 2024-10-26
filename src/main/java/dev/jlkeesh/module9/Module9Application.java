package dev.jlkeesh.module9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class Module9Application {

    public static void main(String[] args) {
        SpringApplication.run(Module9Application.class, args);
    }

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> Optional.of(1L);
    }

}
