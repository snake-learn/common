package com.snake.common.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Đối tượng mô tả thông tin lỗi trả về từ API")
public class AppError {

    @Schema(description = "Mã trạng thái lỗi", example = "400")
    Integer statusCode;

    @Schema(description = "Khóa lỗi dùng để xác định loại lỗi", example = "USER_NOT_FOUND")
    String key;

    @Schema(description = "Thông báo lỗi chi tiết", example = "User not found")
    String message;

    @Schema(description = "Tham số mẫu cho thông báo lỗi", example = "['userId']")
    Object[] templateArgs;

    @Schema(
            description = "Danh sách các lỗi thuộc tính (nếu có)",
            example = "[{\"key\":\"NAME_IS_NULL\",\"message\":\"Name cannot be null\",\"templateArgs\":[\"name\"]}]"
    )
    List<FieldError> fieldErrors;

    @Schema(description = "Nguyên nhân gốc của lỗi (nếu có)")
    String rootCause;

    public static AppError ok() {
        return AppError.builder()
                .statusCode(HttpStatus.OK.value())
                .key("success.200")
                .build();
    }

    public static AppError badRequest() {
        return AppError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .key("errors.400.bad_request")
                .build();
    }
}
