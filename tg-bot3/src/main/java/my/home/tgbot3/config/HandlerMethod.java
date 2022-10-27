package my.home.tgbot3.config;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;

@Data
@Builder
public class HandlerMethod {
    /** Logger that is available to subclasses. */

    private final Object bean;

    private final Method method;

    private final String description;

    private final String directPath;
}
