package dev.jlkeesh.module9.generic;


import dev.jlkeesh.module9.configuration.annotation.CurrentUserId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.generator.EventType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;


@Setter
@Getter
@MappedSuperclass
public class Auditable {

    @CurrentTimestamp(event = EventType.INSERT, source = SourceType.VM)
    private LocalDateTime createdAt;

    @CurrentUserId(event = EventType.INSERT)
    private Long createdBy;

    @CurrentTimestamp(event = EventType.UPDATE, source = SourceType.VM)
    private LocalDateTime updatedAt;

    @CurrentUserId(event = EventType.UPDATE)
    private Long updatedBy;

}
