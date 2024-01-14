package com.makskostyshen.victorycases.event;

import com.makskostyshen.victorycases.backup.BackupService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartupEventListener implements ApplicationListener<ContextRefreshedEvent> {
    private final BackupService backupService;

    @Override
    public void onApplicationEvent(@NonNull final ContextRefreshedEvent event) {
        backupService.updateBackup();
    }
}
