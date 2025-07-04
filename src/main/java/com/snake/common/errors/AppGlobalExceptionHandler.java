package com.snake.common.errors;

import com.snake.common.dtos.wrappers.AppError;
import com.snake.common.dtos.wrappers.AppMetadata;
import com.snake.common.dtos.wrappers.AppResponse;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Xử lý toàn cục các exception cho ứng dụng.
 */

@RestControllerAdvice
public class AppGlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<AppResponse<Void>> handleBusinessException(BusinessException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<AppResponse<Void>> handleValidationException(ValidationException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<AppResponse<Void>> handleNotFoundException(NotFoundException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<AppResponse<Void>> handleDuplicateResourceException(DuplicateResourceException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<AppResponse<Void>> handleResourceException(ResourceException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<AppResponse<Void>> handleUnauthorizedException(UnauthorizedException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<AppResponse<Void>> handleForbiddenException(ForbiddenException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<AppResponse<Void>> handleTimeoutException(TimeoutException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<AppResponse<Void>> handleExternalServiceException(ExternalServiceException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<AppResponse<Void>> handleInternalServerException(InternalServerException ex) {
        val error = AppError.builder()
                .statusCode(ex.getStatusCode().value())
                .key(ex.getKey())
                .message(ex.getMessage())
                .templateArgs(ex.getTemplateArgs())
                .build();
        val metadata = AppMetadata.error(error);

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(AppResponse.error(metadata));
    }
}
