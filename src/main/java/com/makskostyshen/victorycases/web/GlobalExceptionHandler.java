package com.makskostyshen.victorycases.web;

import com.makskostyshen.victorycases.exception.UpdateBackupException;
import com.makskostyshen.victorycases.web.controller.ErrorController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@Slf4j
@Component
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final String defaultResponseView = "redirect:" + ErrorController.ERROR_PATH;
    private final Map<Class<?>, String> exceptionResponseViewRegistry = Map.of(
            UpdateBackupException.class,
            "redirect:" + ErrorController.BACKUP_UPDATE_ERROR_PATH
    );
    @ExceptionHandler(RuntimeException.class)
    public String handle(final Model model, RuntimeException exception) {
        log.error("Error occurred", exception);

        String responseView = exceptionResponseViewRegistry.get(exception.getClass());

        return responseView != null
                ? responseView
                : defaultResponseView;
    }
}
