package com.snake.common.i18ns;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Lớp tiện ích cung cấp các phương thức để lấy thông điệp (message) theo ngôn ngữ hiện tại
 * sử dụng {@link MessageSource} của Spring. Hỗ trợ lấy message dựa trên mã (code) hoặc {@link I18nKey},
 * truyền tham số động và tự động lấy locale hiện tại từ {@link LocaleContextHolder}.
 * </p>
 *
 * <p>
 * Sử dụng để quốc tế hóa (i18n) các thông điệp trong ứng dụng một cách thuận tiện.
 * </p>
 */
@Component
public class I18nProvider {

    /**
     * Đối tượng MessageSource dùng để lấy các thông điệp theo ngôn ngữ.
     */
    private static MessageSource messageSource;

    /**
     * Hàm khởi tạo private để ngăn tạo instance từ bên ngoài.
     */
    private I18nProvider() {
    }

    /**
     * Thiết lập đối tượng MessageSource cho provider.
     *
     * @param source Đối tượng MessageSource cần thiết lập
     */
    public static void setMessageSource(MessageSource source) {
        I18nProvider.messageSource = source;
    }

    /**
     * Lấy thông điệp theo mã code và các tham số truyền vào.
     *
     * @param code Mã thông điệp
     * @param args Tham số truyền vào thông điệp (nếu có)
     * @return Thông điệp đã được dịch theo ngôn ngữ hiện tại
     */
    public static String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * Lấy thông điệp theo {@link I18nKey} và các tham số truyền vào.
     *
     * @param i18n Đối tượng I18nKey chứa mã thông điệp
     * @param args Tham số truyền vào thông điệp (nếu có)
     * @return Thông điệp đã được dịch theo ngôn ngữ hiện tại
     */
    public static String getMessage(I18nKey i18n, Object... args) {
        return messageSource.getMessage(i18n.getKey(), args, LocaleContextHolder.getLocale());
    }
}
