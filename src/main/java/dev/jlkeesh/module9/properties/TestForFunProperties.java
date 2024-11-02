package dev.jlkeesh.module9.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "test")
public class TestForFunProperties {
    private String message;
    private FunType funType = FunType.XYZ;


}
