package my.home.tgbot3.config;

import lombok.extern.slf4j.Slf4j;
import my.home.tgbot3.annotation.CommandMapping;
import my.home.tgbot3.annotation.TgController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Component
@ConditionalOnClass(TelegramBot.class)
@Slf4j
public class HandlerMethodRegistration {
    //тут будет логика автоматом регистрировать аннотации эндпоинты при запуске приложения
    private final Map<String, Object> beanWithTgController;
    private final ApplicationContext applicationContext;

    private Set<HandlerMethod> handlerMethods=new HashSet<>();


    public HandlerMethodRegistration(Map<String, Object> beans, ApplicationContext applicationContext) {
        this.beanWithTgController = applicationContext.getBeansWithAnnotation(TgController.class);
        this.applicationContext = applicationContext;
    }

    public Map<String, Object> getBeansWithTgControllerAnnotation() {
        return beanWithTgController;
    }

    @PostConstruct
    private void findAndConvertBeanToHandlerMethod() {
        for (Object serviceBean : beanWithTgController.values()) {
            log.info("c");
            for (Method declaredMethod : serviceBean.getClass().getDeclaredMethods()) {
                if (declaredMethod.isAnnotationPresent(CommandMapping.class))
                    handlerMethods.add(HandlerMethod.builder().method(declaredMethod).bean(serviceBean).description(declaredMethod.getName()).directPath(declaredMethod.getAnnotation(CommandMapping.class).value()).build());
            }
        }
        log.info("c");
    }
}
