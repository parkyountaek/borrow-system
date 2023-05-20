package com.borrow.system.modulecore.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UpdateBaseEntity extends CreateBaseEntity{
    @LastModifiedDate
    @Column(name = "UPDATE_DTM", updatable = true)
    private LocalDateTime updatedAt;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void initUpdateBaseEntity() {
        this.updatedAt = LocalDateTime.now();
    }
}
