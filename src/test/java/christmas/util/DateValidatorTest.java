package christmas.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    void validDateDoesNotThrowException(int date) {
        assertDoesNotThrow(() -> DateValidator.validateDecemberDateRange(date));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void invalidDateThrowsException(int date) {
        assertThatThrownBy(() -> DateValidator.validateDecemberDateRange(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
