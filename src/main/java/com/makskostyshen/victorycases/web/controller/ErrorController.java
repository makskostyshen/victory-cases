package com.makskostyshen.victorycases.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ErrorController {

    public static final String ERROR_PATH = "/error";
    public static final String BACKUP_UPDATE_ERROR_PATH = ERROR_PATH + "error/backup/update";
    @GetMapping(ERROR_PATH)
    public String getErrorPage(final Model model) {
        return "errors/error";
    }

    @GetMapping(BACKUP_UPDATE_ERROR_PATH)
    public String getBackupUpdateErrorPage(final Model model) {
        return "errors/errorUpdateBackup";
    }
}
