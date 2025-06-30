package com.snake.common.dtos.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.snake.common.errors.AppError;
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
@Schema(
    description = "Metadata trả về từ API, bao gồm thông tin lỗi và phân trang. Các thuộc tính của appError và appPage sẽ được gộp vào response.",
    allOf = {AppError.class, AppPage.class}
)
public class AppMetadata {

    @JsonUnwrapped
    @Schema(description = "Thông tin lỗi (nếu có). Các trường sẽ được gộp vào response.")
    AppError appError;

    @JsonUnwrapped
    @Schema(description = "Thông tin phân trang (nếu có). Các trường sẽ được gộp vào response.")
    AppPage appPage;
}
