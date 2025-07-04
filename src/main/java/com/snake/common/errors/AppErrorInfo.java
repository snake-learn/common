package com.snake.common.errors;

import com.snake.common.i18ns.I18nKey;
import org.springframework.http.HttpStatus;

public interface AppErrorInfo extends I18nKey {
    HttpStatus getStatusCode();
}
