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

/**
 * Cấu hình nguồn thông điệp (MessageSource) hỗ trợ đa ngôn ngữ và thiết lập locale mặc định.
 */
@Configuration
@RequiredArgsConstructor
public class MessageSourceConfig {

    /**
     * Ngôn ngữ mặc định của ứng dụng, lấy từ cấu hình app.lang.default.
     */
    @Value("${app.lang.default:en}")
    private String defaultLocale;

    /**
     * Danh sách các nguồn thông điệp được sử dụng để tổng hợp.
     */
    private final List<MessageSource> messageSources;

    /**
     * Tạo bean MessageSource tổng hợp từ nhiều nguồn thông điệp.
     *
     * @return MessageSource đã được tổng hợp.
     */
    @Bean
    @Primary
    public MessageSource getMessageSource() {
        val source = new CompositeMessageSource(messageSources);
        I18nProvider.setMessageSource(source);

        return source;
    }

    /**
     * Tạo bean LocaleResolver để xác định locale dựa trên header của request.
     *
     * @return LocaleResolver với locale mặc định đã được thiết lập.
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag(defaultLocale));
        return localeResolver;
    }
}
