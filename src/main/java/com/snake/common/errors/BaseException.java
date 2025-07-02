package com.snake.common.errors;

import com.snake.common.i18ns.I18nKey;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseException extends RuntimeException implements I18nKey {

    final HttpStatus statusCode;

    final String key;

    Object[] templateArgs;

    protected BaseException(@NonNull String tag, @NonNull HttpStatus httpStatus, @NonNull String key) {
        super(tag);
        this.statusCode = httpStatus;
        this.key = key;
    }

    protected BaseException(@NonNull String tag, @NonNull HttpStatus httpStatus, @NonNull String key, Object... templateArgs) {
        super(tag);
        this.statusCode = httpStatus;
        this.key = key;
        this.templateArgs = templateArgs;
    }

    protected BaseException(@NonNull String tag, Throwable cause, @NonNull HttpStatus httpStatus, @NonNull String key) {
        super(tag, cause);
        this.statusCode = httpStatus;
        this.key = key;
    }

    protected BaseException(@NonNull String tag, Throwable cause, @NonNull HttpStatus httpStatus, @NonNull String key, Object... templateArgs) {
        super(tag, cause);
        this.statusCode = httpStatus;
        this.key = key;
        this.templateArgs = templateArgs;
    }

    /**
     * Lấy nguyên nhân gốc rễ của ngoại lệ.
     * <p>
     * Phương thức này duyệt qua chuỗi các nguyên nhân để tìm nguyên nhân đầu
     */
    public Throwable getRootCause() {
        Throwable cause = this;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }
}
