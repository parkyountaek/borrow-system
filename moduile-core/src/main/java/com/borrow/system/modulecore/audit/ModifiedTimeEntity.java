package com.borrow.system.modulecore.audit;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ModifiedTimeEntity extends CreatedTimeEntity {
    @UpdateTimestamp
    private LocalDateTime modifiedTime;

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }
}
