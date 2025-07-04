package com.snake.common.dtos.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.snake.common.utils.RequestUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.context.i18n.LocaleContextHolder;

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

    @Schema(description = "Ngôn ngữ của response, ví dụ: vi, en, ...")
    String locale;

    @Schema(description = "Đường dẫn của API được gọi, ví dụ: /api/v1/users")
    String path;

    public static AppMetadata ok() {
        return AppMetadata.builder()
                .appError(AppError.ok())
                .locale(LocaleContextHolder.getLocale().getLanguage())
                .path(RequestUtils.getPath())
                .build();
    }

    public static AppMetadata ok(AppError appError) {
        return AppMetadata.builder()
                .appError(appError)
                .locale(LocaleContextHolder.getLocale().getLanguage())
                .path(RequestUtils.getPath())
                .build();
    }

    public static AppMetadata ok(AppPage appPage) {
        return AppMetadata.builder()
                .appError(AppError.ok())
                .appPage(appPage)
                .locale(LocaleContextHolder.getLocale().getLanguage())
                .path(RequestUtils.getPath())
                .build();
    }

    public static AppMetadata ok(AppError appError, AppPage appPage) {
        return AppMetadata.builder()
                .appError(appError)
                .appPage(appPage)
                .locale(LocaleContextHolder.getLocale().getLanguage())
                .path(RequestUtils.getPath())
                .build();
    }

    public static AppMetadata error(AppError appError) {
        return AppMetadata.builder()
                .appError(appError)
                .locale(LocaleContextHolder.getLocale().getLanguage())
                .path(RequestUtils.getPath())
                .build();
    }
}
