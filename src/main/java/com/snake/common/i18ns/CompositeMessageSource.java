package com.snake.common.i18ns;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
public class CompositeMessageSource implements MessageSource {

    private static final String MESSAGE_FORMAT = "CompositeMessageSource class: {}, message: {}";

    private final List<MessageSource> sources = new ArrayList<>();

    public CompositeMessageSource(List<MessageSource> sources) {
        if (CollectionUtils.isNotEmpty(sources)) {
            this.sources.addAll(sources);
        }
    }

    @Override
    public String getMessage(@NonNull String code, Object[] args, String defaultMessage, @NonNull Locale locale) {
        for (var source : sources) {
            try {
                return source.getMessage(code, args, locale);
            } catch (NoSuchMessageException ex) {
                log.error(
                        MESSAGE_FORMAT,
                        source.getClass().getName(),
                        ex.getMessage(),
                        ex
                );
            }
        }

        if (StringUtils.isBlank(defaultMessage)) {
            return code;
        }

        return defaultMessage;
    }

    @NonNull
    @Override
    public String getMessage(@NonNull String code, Object[] args, @NonNull Locale locale) throws NoSuchMessageException {
        for (var source : sources) {
            try {
                return source.getMessage(code, args, locale);
            } catch (NoSuchMessageException ex) {
                log.error(
                        MESSAGE_FORMAT,
                        source.getClass().getName(),
                        ex.getMessage(),
                        ex
                );
            }
        }

        return code;
    }

    @NonNull
    @Override
    public String getMessage(@NonNull MessageSourceResolvable resolvable, @NonNull Locale locale) {
        for (var source : sources) {
            try {
                return source.getMessage(resolvable, locale);
            } catch (NoSuchMessageException ex) {
                log.error(
                        MESSAGE_FORMAT,
                        source.getClass().getName(),
                        ex.getMessage(),
                        ex
                );
            }
        }

        return Objects.requireNonNull(resolvable.getDefaultMessage());

    }
}
