package com.snake.common.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Đối tượng mô tả thông tin thuộc tính lỗi trả về từ API")
public class FieldError {

    @Schema(description = "Khóa lỗi dùng để xác định loại lỗi", example = "NAME_IS_NULL")
    String key;

    @Schema(description = "Thông báo lỗi chi tiết", example = "Name cannot be null")
    String message;

    @Schema(description = "Tham số mẫu cho thông báo lỗi", example = "['name']")
    Object[] templateArgs;
}
