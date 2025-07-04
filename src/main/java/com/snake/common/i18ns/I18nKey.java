package com.snake.common.i18ns;

/**
 * Định nghĩa một interface cho khoá i18n dùng trong đa ngôn ngữ.
 * Các implement cần trả về khoá duy nhất để tra cứu thông điệp.
 */
public interface I18nKey {
    /**
     * Lấy khoá i18n duy nhất.
     *
     * @return khoá i18n dưới dạng chuỗi
     */
    String getKey();
}
