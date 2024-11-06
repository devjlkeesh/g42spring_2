package dev.jlkeesh.module9.properties;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.stream.Stream;

public record WhiteListProperty(String method, List<String> patterns) {

    public Stream<RequestMatcher> getRequestMatcher() {
        if (patterns == null || patterns.isEmpty()) {
            return Stream.empty();
        }
        return patterns.stream()
                .map(pattern -> new AntPathRequestMatcher(pattern, method, false));
    }
}
