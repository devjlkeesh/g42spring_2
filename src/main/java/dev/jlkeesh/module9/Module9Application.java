package dev.jlkeesh.module9;

import dev.jlkeesh.module9.configuration.security.UserSession;
import dev.jlkeesh.module9.properties.JwtProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
/*@EnableConfigurationProperties(
        value = {
                JwtProperties.class
        }
)*/
public class Module9Application {

    public static void main(String[] args) {
        SpringApplication.run(Module9Application.class, args);
    }

    @Bean
    public AuditorAware<Long> auditorProvider(UserSession userSession, DataSource dataSource) {
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
