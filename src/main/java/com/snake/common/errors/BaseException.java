package com.snake.common.errors;

import com.snake.common.i18ns.I18nKey;
import lombok.NonNull;

/**
 * Lớp ngoại lệ cơ sở cho các ngoại lệ tuỳ chỉnh trong ứng dụng.
 * Hỗ trợ đa ngôn ngữ thông qua interface {@link I18nKey}.
 */
@SuppressWarnings("unused")
public abstract class BaseException extends RuntimeException implements AppErrorInfo {

    /**
     * Khởi tạo ngoại lệ với thông điệp chi tiết.
     *
     * @param tag thông điệp chi tiết (không được null)
     */
    protected BaseException(@NonNull String tag) {
        super(tag);
    }

    /**
     * Khởi tạo một ngoại lệ BaseException mới với tag chi tiết và nguyên nhân cụ thể.
     *
     * @param tag   thông điệp chi tiết (không được null)
     * @param cause nguyên nhân của ngoại lệ (có thể null)
     */
    protected BaseException(@NonNull String tag, Throwable cause) {
        super(tag, cause);
    }

    /**
     * Lấy ra nguyên nhân gốc (root cause) của ngoại lệ này.
     * Duyệt qua chuỗi nguyên nhân cho đến khi không còn nguyên nhân lồng bên trong.
     *
     * @return Throwable là nguyên nhân gốc của ngoại lệ
     */
    public Throwable getRootCause() {
        Throwable cause = this;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }

    /**
     * Lấy ra thông điệp (message) của nguyên nhân gốc của ngoại lệ này.
     * Nếu nguyên nhân gốc không có message, trả về chuỗi toString() của nó.
     *
     * @return Thông điệp của nguyên nhân gốc hoặc chuỗi toString() nếu không có message
     */
    public String getRootMessage() {
        Throwable rootCause = this.getRootCause();
        return rootCause.getMessage() != null ? rootCause.getMessage() : rootCause.toString();
    }
}
