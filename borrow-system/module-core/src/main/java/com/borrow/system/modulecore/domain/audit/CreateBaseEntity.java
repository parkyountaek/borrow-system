package com.borrow.system.modulecore.domain.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CreateBaseEntity {
    @CreatedDate
    @Column(name = "CREATE_DTM", updatable = false)
    private LocalDateTime createdAt;

    public void initCreateBaseEntity() {
        this.createdAt = LocalDateTime.now();
    }
}