package dev.jlkeesh.module9.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;


@Getter
@Setter
@ConfigurationProperties(prefix = "white.list")
public class WhiteListProperties {
    private List<WhiteListProperty> matchers;

    public RequestMatcher[] getRequestMatchers() {
        if (matchers == null || matchers.isEmpty()) {
            return new RequestMatcher[0];
        }
        return matchers.stream()
                .flatMap(WhiteListProperty::getRequestMatcher)
                .toArray(RequestMatcher[]::new);
    }
}
