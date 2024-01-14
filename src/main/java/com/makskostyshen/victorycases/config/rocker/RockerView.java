package com.makskostyshen.victorycases.config.rocker;

import com.fizzed.rocker.BindableRockerModel;
import com.fizzed.rocker.Rocker;
import com.fizzed.rocker.RockerModel;
import com.fizzed.rocker.runtime.OutputStreamOutput;
import com.fizzed.rocker.runtime.RockerRuntime;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class RockerView implements View {

    private String path;
    private Object[] arguments;

    public RockerView(String path, Object... arguments) {
        this.path = path;
        this.arguments = arguments;
    }

    @Override
    public void render(
            @Nullable final Map<String, ?> model,
            @NonNull final HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        BindableRockerModel template = Rocker.template(path);
        bind(template, model);
        response.setContentType(getContentType() == null ? MediaType.TEXT_HTML_VALUE : getContentType());
        template.render((type, charset) -> {
            try {
                response.setCharacterEncoding(charset);
                return new OutputStreamOutput(type, response.getOutputStream(), charset);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private void bind(BindableRockerModel template, Map<String, ?> model) {
        if (model != null) {
            if (model.containsKey("arguments")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) model.get("arguments");
                template.bind(map);
            }
            RockerModel rocker = RockerRuntime.getInstance().getBootstrap().model(path);
            for (String name : getModelArgumentNames(rocker)) {
                if (model.containsKey(name)) {
                    template.bind(name, model.get(name));
                }
            }
        }
    }

    @Override
    @Nullable
    public String getContentType() {
        return MediaType.TEXT_HTML_VALUE;
    }

    static private String[] getModelArgumentNames(RockerModel model) {
        try {
            Method f = model.getClass().getMethod("getArgumentNames");
            return (String[]) f.invoke(null);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to read getArgumentNames static method from template");
        }
    }

}