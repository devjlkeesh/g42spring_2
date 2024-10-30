package dev.jlkeesh.module9.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorData(String message, Object... params) {
}
