package dev.jlkeesh.module9;

import dev.jlkeesh.module9.configuration.security.UserSession;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
/*@OpenAPIDefinition(
        info = @Info(
                title = "This project is for learning only",
                description = "This project is for learning only(description)",
                version = "0.0.12",
                contact = @Contact(
                        name = "G42",
                        url = "https://g42.io",
                        email = "g42@gmail.com"
                )
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)*/
public class Module9Application {

    public static void main(String[] args) {
        SpringApplication.run(Module9Application.class, args);
    }

    @Bean
    public AuditorAware<Long> auditorProvider(UserSession userSession) {
        return () -> Optional.of(userSession.requireUserId());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }

}
