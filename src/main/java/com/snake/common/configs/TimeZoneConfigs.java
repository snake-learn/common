package com.snake.common.configs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Cấu hình múi giờ cho toàn bộ ứng dụng.
 * <p>
 * Điều này giúp đảm bảo tất cả các thao tác về thời gian đều sử dụng cùng một múi giờ.
 * </p>
 */
@Configuration
@RequiredArgsConstructor
public class TimeZoneConfigs {

  /**
   * Thiết lập múi giờ mặc định cho JVM là UTC.
   * Phương thức này sẽ được gọi tự động sau khi bean được khởi tạo.
   */
  @PostConstruct
  void setDefaultTimeZone() {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
  }
}
