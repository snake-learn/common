package com.snake.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Lớp cơ sở trừu tượng cung cấp các thuộc tính kiểm toán chung cho các thực thể.
 * <p>
 * Lớp này bao gồm thông tin về người tạo, thời gian tạo, người cập nhật,
 * và thời gian cập nhật của thực thể. Nó được sử dụng làm lớp cha cho các thực thể khác.
 * <p>
 * Các thuộc tính được tự động cập nhật bởi Spring Data JPA thông qua {@link AuditingEntityListener}.
 */
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    /**
     * ID của người tạo thực thể.
     * <p>
     * Thuộc tính này được tự động điền bởi Spring Data JPA.
     */
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    /**
     * Thời gian tạo thực thể.
     * <p>
     * Thuộc tính này được tự động điền bởi Hibernate khi thực thể được tạo.
     */
    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_time", nullable = false, updatable = false)
    private Instant createdTime;

    /**
     * ID của người cập nhật thực thể.
     * <p>
     * Thuộc tính này được tự động điền bởi Spring Data JPA khi thực thể được cập nhật.
     */
    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;

    /**
     * Thời gian cập nhật thực thể.
     * <p>
     * Thuộc tính này được tự động cập nhật bởi Hibernate khi thực thể được sửa đổi.
     */
    @Version
    @UpdateTimestamp
    @LastModifiedDate
    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_time")
    private Instant updatedTime;
}
