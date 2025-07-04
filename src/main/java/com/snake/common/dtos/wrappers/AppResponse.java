package com.snake.common.dtos.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Đối tượng phản hồi chuẩn của API, bao gồm dữ liệu trả về và metadata.")
public class AppResponse<T> {
    @Schema(
        description = "Dữ liệu trả về từ API. Có thể là một đối tượng, danh sách hoặc null.",
        example = "{\"id\":1,\"name\":\"example\"}"
    )
    T data;

    @Schema(
        description = "Metadata đi kèm phản hồi, bao gồm thông tin lỗi và phân trang.",
        implementation = AppMetadata.class
    )
    AppMetadata metadata;

    public static <T> AppResponse<T> ok(T data) {
        return AppResponse.<T>builder()
                .data(data)
                .metadata(AppMetadata.ok())
                .build();
    }

    public static AppResponse<Void> error(AppMetadata metadata) {
        return AppResponse.<Void>builder()
                .metadata(metadata)
                .build();
    }
}
