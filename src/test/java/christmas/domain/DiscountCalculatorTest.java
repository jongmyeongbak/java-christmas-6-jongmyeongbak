package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountCalculatorTest {

    private final DiscountCalculator discountCalculator = new DiscountCalculator();

    @ParameterizedTest
    @CsvSource({"1,1000", "25,3400"})
    void calculateDdayDiscount_UntilChristmas_ProvidesCorrectDiscount(int date, int expectedAmount) {
        assertThat(discountCalculator.calculateDdayDiscount(LocalDate.of(2023, 12, date)))
                .isEqualTo(expectedAmount);
    }

    @Test
    void calculateDdayDiscount_AfterChristmas_ProvidesNoDiscount() {
        assertThat(discountCalculator.calculateDdayDiscount(LocalDate.of(2023, 12, 26)))
                .isZero();
    }
}
