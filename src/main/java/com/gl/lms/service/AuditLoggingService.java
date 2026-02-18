package com.gl.lms.service;

import com.gl.lms.entity.AuditLogging;
import com.gl.lms.repository.AuditLoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditLoggingService {

    @Autowired
    private AuditLoggingRepository auditLoggingRepository;

    public void log(String username,
                    String action,
                    Integer resourceId,
                    String resourceType) {

        AuditLogging log =
                new AuditLogging(username, action, resourceId, resourceType);

        auditLoggingRepository.save(log);
    }
}
