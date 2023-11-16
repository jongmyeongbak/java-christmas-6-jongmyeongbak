package christmas.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    void validDateDoesNotThrowException(int date) {
        assertThatCode(() -> DateValidator.validateDecemberDateRange(date))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void invalidDateThrowsException(int date) {
        assertThatThrownBy(() -> DateValidator.validateDecemberDateRange(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다.");
    }
}
