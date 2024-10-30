package dev.jlkeesh.module9.dto.auth;

import java.util.List;

public record UserSessionData(
        Long id,
        String username,
        String email,
        List<String> author
) {

}
