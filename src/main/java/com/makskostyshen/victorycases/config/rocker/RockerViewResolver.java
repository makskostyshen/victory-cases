package com.makskostyshen.victorycases.config.rocker;

import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class RockerViewResolver implements ViewResolver, Ordered {

    private final String prefix;
    private final String suffix;

    public RockerViewResolver(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    @Nullable
    public View resolveViewName(@NonNull final String viewName, @NonNull final Locale locale) {
        return new RockerView(prefix + viewName + suffix);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }
}
