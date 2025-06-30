package com.snake.common.configs;

import com.snake.common.i18ns.CompositeMessageSource;
import com.snake.common.i18ns.I18nProvider;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class MessageSourceConfig {

    @Value("${app.lang.default:en}")
    private String defaultLocale;

    private final List<MessageSource> messageSources;

    @Bean
    @Primary
    public MessageSource getMessageSource() {
        val source = new CompositeMessageSource(messageSources);
        I18nProvider.setMessageSource(source);

        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag(defaultLocale));
        return localeResolver;
    }
}
