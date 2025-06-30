package com.snake.common.enums.converters.jakarta;

import com.snake.common.enums.AppState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Bộ chuyển đổi trạng thái {@link AppState} cho Jakarta Persistence.
 * <p>
 * Class này được sử dụng để chuyển đổi giữa enum {@link AppState} và giá trị số nguyên (Integer)
 * khi lưu trữ hoặc truy xuất dữ liệu từ cơ sở dữ liệu.
 * <p>
 * Các chức năng chính:
 * <ul>
 *     <li>{@link #convertToDatabaseColumn(AppState)} - Chuyển đổi enum {@code AppState} thành số nguyên để lưu vào cơ sở dữ liệu.</li>
 *     <li>{@link #convertToEntityAttribute(Integer)} - Chuyển đổi số nguyên từ cơ sở dữ liệu thành enum {@code AppState}.</li>
 * </ul>
 * <p>
 * Nếu xảy ra lỗi trong quá trình chuyển đổi, giá trị {@code null} sẽ được trả về và lỗi sẽ được ghi log.
 */
@Slf4j
@Converter(autoApply = true)
public class AppStateConverter implements AttributeConverter<AppState, Integer> {

    /**
     * Chuyển đổi enum {@link AppState} thành số nguyên để lưu vào cơ sở dữ liệu.
     *
     * @param att Trạng thái {@code AppState} cần chuyển đổi.
     * @return Giá trị số nguyên tương ứng với trạng thái, hoặc {@code null} nếu trạng thái là {@code null}.
     */
    @Override
    public Integer convertToDatabaseColumn(AppState att) {
        if (Objects.isNull(att)) {
            return null;
        }
        return att.getKey();
    }

    /**
     * Chuyển đổi số nguyên từ cơ sở dữ liệu thành enum {@link AppState}.
     *
     * @param dbData Giá trị số nguyên từ cơ sở dữ liệu.
     * @return Trạng thái {@code AppState} tương ứng, hoặc {@code null} nếu giá trị là {@code null}.
     * @throws IllegalArgumentException nếu giá trị không hợp lệ.
     */
    @Override
    public AppState convertToEntityAttribute(Integer dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }

        try {
            return AppState.of(dbData);
        } catch (IllegalArgumentException e) {
            log.error("AppStateConverter error when convert: mess {}", e.getMessage(), e);
            return null;
        }
    }
}

