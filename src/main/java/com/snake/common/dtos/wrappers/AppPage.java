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
@Schema(description = "Thông tin phân trang trả về từ API")
public class AppPage {
    @Schema(description = "Tổng số phần tử", example = "100")
    Long totalElements;

    @Schema(description = "Tổng số trang", example = "10")
    Integer totalPages;

    @Schema(description = "Có trang trước không", example = "false")
    Boolean hasPrevious;

    @Schema(description = "Có trang sau không", example = "true")
    Boolean hasNext;

    @Schema(description = "Số trang hiện tại (bắt đầu từ 0)", example = "0")
    Integer pageNumber;

    @Schema(description = "Kích thước trang", example = "10")
    Integer pageSize;
}
