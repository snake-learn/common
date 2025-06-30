package com.snake.common.i18ns;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class I18nProvider {

    private static MessageSource messageSource;

    private I18nProvider() {
    }

    public static void setMessageSource(MessageSource source) {
        I18nProvider.messageSource = source;
    }

    public static String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
