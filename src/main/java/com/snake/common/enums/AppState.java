package com.snake.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Đại diện cho trạng thái của đối tượng.
 * <p>
 * Enum này được sử dụng để định nghĩa và quản lý các trạng thái có thể có của đối tượng:
 * <ul>
 *     <li>{@link #INACTIVE} - Đối tượng đang không hoạt động.</li>
 *     <li>{@link #ACTIVE} - Đối tượng đang hoạt động.</li>
 * </ul>
 * Mỗi trạng thái được liên kết với một key số nguyên và codeName dùng để đa ngôn ngữ.
 * <p>
 * Enum cung cấp các phương thức để tuần tự hóa và giải tuần tự hóa:
 * <ul>
 *     <li>{@link #of(int)} - Chuyển đổi một khóa số nguyên thành trạng thái {@code AppState} tương ứng.</li>
 *     <li>{@link #getKey()} - Lấy key (số nguyên) của trạng thái hiện tại.</li>
 *     <li>{@link #getCodeName()} ()} - Lấy codeName của trạng thái hiện tại. Dùng để đa ngôn ngữ.</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public enum AppState {
    INACTIVE(0, "APP_STATE.INACTIVE"),
    ACTIVE(1, "APP_STATE.ACTIVE"),
    ;

    private final int key;
    private final String codeName;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static AppState of(int data) {
        return Stream.of(values())
                .filter(s -> Objects.equals(s.getKey(), data))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(String.format(
                                "Invalid %s: %s",
                                AppState.class.getSimpleName(),
                                data
                        ))
                );
    }

    @JsonValue
    public int getKey() {
        return this.key;
    }
}
