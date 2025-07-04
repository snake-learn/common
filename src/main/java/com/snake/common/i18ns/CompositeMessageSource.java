package com.snake.common.i18ns;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * <p>
 * Lớp {@code CompositeMessageSource} cho phép kết hợp nhiều {@link MessageSource} lại với nhau.
 * Khi lấy thông điệp, lớp sẽ lần lượt truy vấn từng nguồn cho đến khi tìm thấy thông điệp phù hợp.
 * Nếu không tìm thấy, sẽ trả về thông điệp mặc định hoặc mã code.
 * </p>
 *
 * <p>
 * Lớp này hữu ích khi bạn muốn gom nhiều nguồn thông điệp (message) lại để phục vụ cho việc quốc tế hóa (i18n).
 * </p>
 */
@Slf4j
public class CompositeMessageSource implements MessageSource {

    /**
     * Định dạng log cho các thông báo lỗi khi không tìm thấy message.
     */
    private static final String MESSAGE_FORMAT = "CompositeMessageSource class: {}, message: {}";

    /**
     * Danh sách các nguồn {@link MessageSource} được kết hợp.
     */
    private final List<MessageSource> sources = new ArrayList<>();

    /**
     * Khởi tạo {@code CompositeMessageSource} với danh sách các nguồn {@link MessageSource}.
     *
     * @param sources Danh sách các nguồn message source cần kết hợp
     */
    public CompositeMessageSource(List<MessageSource> sources) {
        if (CollectionUtils.isNotEmpty(sources)) {
            this.sources.addAll(sources);
        }
    }

    /**
     * Lấy thông điệp dựa trên mã code, tham số, thông điệp mặc định và locale.
     * Sẽ lần lượt tìm kiếm trong các nguồn, nếu không tìm thấy sẽ trả về thông điệp mặc định hoặc mã code.
     *
     * @param code           Mã thông điệp
     * @param args           Tham số truyền vào thông điệp (nếu có)
     * @param defaultMessage Thông điệp mặc định nếu không tìm thấy
     * @param locale         Ngôn ngữ cần lấy thông điệp
     * @return Thông điệp đã được dịch hoặc thông điệp mặc định/mã code nếu không tìm thấy
     */
    @Override
    public String getMessage(@NonNull String code, Object[] args, String defaultMessage, @NonNull Locale locale) {
        for (var source : sources) {
            try {
                return source.getMessage(code, args, locale);
            } catch (NoSuchMessageException ex) {
                log.error(
                        MESSAGE_FORMAT,
                        source.getClass().getName(),
                        ex.getMessage(),
                        ex
                );
            }
        }

        if (StringUtils.isBlank(defaultMessage)) {
            return code;
        }

        return defaultMessage;
    }

    /**
     * Lấy thông điệp dựa trên mã code, tham số và locale.
     * Sẽ lần lượt tìm kiếm trong các nguồn, nếu không tìm thấy sẽ trả về mã code.
     *
     * @param code   Mã thông điệp
     * @param args   Tham số truyền vào thông điệp (nếu có)
     * @param locale Ngôn ngữ cần lấy thông điệp
     * @return Thông điệp đã được dịch hoặc mã code nếu không tìm thấy
     * @throws NoSuchMessageException nếu không tìm thấy thông điệp và không có thông điệp mặc định
     */
    @NonNull
    @Override
    public String getMessage(@NonNull String code, Object[] args, @NonNull Locale locale) throws NoSuchMessageException {
        for (var source : sources) {
            try {
                return source.getMessage(code, args, locale);
            } catch (NoSuchMessageException ex) {
                log.error(
                        MESSAGE_FORMAT,
                        source.getClass().getName(),
                        ex.getMessage(),
                        ex
                );
            }
        }

        return code;
    }

    /**
     * Lấy thông điệp dựa trên {@link MessageSourceResolvable} và locale.
     * Sẽ lần lượt tìm kiếm trong các nguồn, nếu không tìm thấy sẽ trả về thông điệp mặc định của resolvable.
     *
     * @param resolvable Đối tượng chứa thông tin về mã thông điệp và tham số
     * @param locale     Ngôn ngữ cần lấy thông điệp
     * @return Thông điệp đã được dịch hoặc thông điệp mặc định nếu không tìm thấy
     */
    @NonNull
    @Override
    public String getMessage(@NonNull MessageSourceResolvable resolvable, @NonNull Locale locale) {
        for (var source : sources) {
            try {
                return source.getMessage(resolvable, locale);
            } catch (NoSuchMessageException ex) {
                log.error(
                        MESSAGE_FORMAT,
                        source.getClass().getName(),
                        ex.getMessage(),
                        ex
                );
            }
        }

        return Objects.requireNonNull(resolvable.getDefaultMessage());

    }
}
