package com.snake.common.errors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

/**
 * Ngoại lệ dành cho các lỗi liên quan đến nghiệp vụ.
 * <p>
 * Lớp này mở rộng từ {@link BaseException} và cung cấp thêm thuộc tính {@code key}
 * để định danh lỗi nghiệp vụ cụ thể.
 * </p>
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusinessException extends BaseException {

    private static final String TAG = "BusinessException";

    /**
     * Khởi tạo ngoại lệ với khóa định danh.
     *
     * @param key Khóa định danh lỗi nghiệp vụ.
     */
    public BusinessException(@NonNull String key) {
        super(TAG, HttpStatus.CONFLICT, key);
    }

    /**
     * Khởi tạo ngoại lệ với khóa định danh và nguyên nhân.
     *
     * @param key   Khóa định danh lỗi nghiệp vụ.
     * @param cause Nguyên nhân gốc rễ của ngoại lệ.
     */
    public BusinessException(@NonNull String key, Throwable cause) {
        super(TAG, cause, HttpStatus.CONFLICT, key);
    }

}
