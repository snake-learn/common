package com.snake.common.errors;

import com.snake.common.i18ns.I18nProvider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Ngoại lệ dùng để biểu thị trường hợp quá thời gian xử lý (timeout) khi thực hiện một tác vụ hoặc gọi dịch vụ.
 * </p>
 * <ul>
 *   <li>Thường được sử dụng khi hệ thống hoặc dịch vụ bên ngoài không phản hồi trong khoảng thời gian cho phép.</li>
 *   <li>Lưu trữ mã lỗi, thông báo, tham số mẫu và mã trạng thái HTTP liên quan.</li>
 *   <li>Hỗ trợ đa ngôn ngữ thông qua {@link I18nProvider}.</li>
 * </ul>
 */
@Getter
@Setter
@SuppressWarnings("unused")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeoutException extends BaseException {
    /**
     * Tên class dùng làm tag cho log.
     */
    private static final String TAG = TimeoutException.class.getSimpleName();

    /**
     * Mã trạng thái HTTP trả về cho client.
     */
    HttpStatus statusCode;

    /**
     * Mã lỗi nghiệp vụ (key) dùng để tra cứu thông báo.
     */
    String key;

    /**
     * Thông báo lỗi đã được nội địa hóa.
     */
    String message;

    /**
     * Danh sách tham số truyền vào mẫu thông báo.
     */
    Object[] templateArgs;

    /**
     * Khởi tạo ngoại lệ với thông tin lỗi.
     *
     * @param info Thông tin lỗi nghiệp vụ
     */
    public TimeoutException(@NonNull AppErrorInfo info) {
        super(TAG);
        this.statusCode = info.getStatusCode();
        this.key = info.getKey();
        this.message = I18nProvider.getMessage(key);
        this.templateArgs = null;
    }

    /**
     * Khởi tạo ngoại lệ với thông tin lỗi và tham số mẫu cho thông báo.
     *
     * @param info Thông tin lỗi nghiệp vụ
     * @param templateArgs Tham số truyền vào mẫu thông báo
     */
    public TimeoutException(@NonNull AppErrorInfo info, Object... templateArgs) {
        super(TAG);
        this.statusCode = info.getStatusCode();
        this.key = info.getKey();
        this.message = I18nProvider.getMessage(key, templateArgs);
        this.templateArgs = templateArgs;
    }

    /**
     * Khởi tạo ngoại lệ với thông tin lỗi và nguyên nhân gốc.
     *
     * @param info Thông tin lỗi nghiệp vụ
     * @param cause Nguyên nhân gốc của ngoại lệ
     */
    public TimeoutException(@NonNull AppErrorInfo info, Throwable cause) {
        super(TAG, cause);
        this.statusCode = info.getStatusCode();
        this.key = info.getKey();
        this.message = I18nProvider.getMessage(key);
        this.templateArgs = null;
    }

    /**
     * Khởi tạo ngoại lệ với thông tin lỗi, nguyên nhân gốc và tham số mẫu cho thông báo.
     *
     * @param info Thông tin lỗi nghiệp vụ
     * @param cause Nguyên nhân gốc của ngoại lệ
     * @param templateArgs Tham số truyền vào mẫu thông báo
     */
    public TimeoutException(@NonNull AppErrorInfo info, Throwable cause, Object... templateArgs) {
        super(TAG, cause);
        this.statusCode = info.getStatusCode();
        this.key = info.getKey();
        this.message = I18nProvider.getMessage(key);
        this.templateArgs = templateArgs;
    }
}

