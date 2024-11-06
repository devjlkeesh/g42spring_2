package dev.jlkeesh.module9.dto.mail;

import java.time.LocalDate;

public record Order(
        String salesrepNumber,
        String orderNumber,
        String ioGuid,
        LocalDate lastUpdateDate,
        String lastUpdatedByUser) {
}
