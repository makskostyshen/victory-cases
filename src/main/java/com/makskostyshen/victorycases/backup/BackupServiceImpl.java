package com.makskostyshen.victorycases.backup;

import com.makskostyshen.victorycases.exception.UpdateBackupException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class BackupServiceImpl implements BackupService {
    @Value("${app.cases.fileName}")
    private String originFileName;

    @Value("${app.cases.backup.fileName}")
    private String backupFileName;

    @Value("${app.cases.backup.max-update-attempts}")
    private Integer maxUpdateBackupAttempts;

    @Override
    public void updateBackup() {
        int errorsCount = 0;

        while (true) {
            try {
                copyFileContents(originFileName, backupFileName);
                break;
            } catch (final IOException e) {
                if (errorsCount > 0 && errorsCount < maxUpdateBackupAttempts) {
                    log.warn("Backup file have not been updated", e);
                    errorsCount++;
                }
                if (errorsCount < maxUpdateBackupAttempts) {
                    errorsCount++;
                } else {
                    throw new UpdateBackupException(e);
                }
            }
        }
    }

    private void copyFileContents(final String sourcePath, final String destinationPath) throws IOException {
        Files.copy(
                Path.of(sourcePath),
                Path.of(destinationPath),
                StandardCopyOption.REPLACE_EXISTING);
    }
}
