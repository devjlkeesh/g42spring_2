package dev.jlkeesh.module9.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jlkeesh.module9.dto.BaseResponse;
import dev.jlkeesh.module9.dto.ErrorData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

    private final ObjectMapper jacksonObjectMapper;
    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandler.class);


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.error("unauthorized request", e);
        response.setHeader("Content-Type", "application/json");
        response.setStatus(401);
        ServletOutputStream outputStream = response.getOutputStream();
        ErrorData error = new ErrorData("unauthorized");
        BaseResponse<Void> responseBody = new BaseResponse<>(error);
        jacksonObjectMapper.writeValue(outputStream, responseBody);
    }
}
