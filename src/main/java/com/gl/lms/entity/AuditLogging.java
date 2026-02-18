package com.gl.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
public class AuditLogging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String action;

    private Integer resourceId;

    private String resourceType;

    private LocalDateTime timestamp;

    public AuditLogging(String username, String action,
                        Integer resourceId, String resourceType) {
        this.username = username;
        this.action = action;
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.timestamp = LocalDateTime.now();
    }
}
