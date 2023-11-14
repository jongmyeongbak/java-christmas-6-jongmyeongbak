package christmas.domain;

import static christmas.util.Constants.STAR_DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountCalculatorTest {
    private static final int WEEKDAY_DISCOUNT = 2023;
    private static final int WEEKEND_DISCOUNT = 2023;

    private final DiscountCalculator discountCalculator = new DiscountCalculator();

    @ParameterizedTest
    @CsvSource({"1,1000", "25,3400"})
    void calculateDDayDiscount_UntilChristmas_ProvidesCorrectDiscount(int date, int expectedAmount) {
        assertThat(discountCalculator.calculateDDayDiscount(LocalDate.of(2023, 12, date)))
                .isEqualTo(expectedAmount);
    }

    @Test
    void calculateDDayDiscount_AfterChristmas_ProvidesNoDiscount() {
        assertThat(discountCalculator.calculateDDayDiscount(LocalDate.of(2023, 12, 26)))
                .isZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 25, 31})
    void calculateWeekdayDiscount_OnWeekday_ProvidesCorrectDiscount(int weekday) {
        Order mockOrder = mock(Order.class);
        when(mockOrder.countTotalQuantityForCategory(Menu.Category.DESSERT)).thenReturn(3);

        assertThat(discountCalculator.calculateWeekdayDiscount(LocalDate.of(2023, 12, weekday), mockOrder))
                .isEqualTo(3 * WEEKDAY_DISCOUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 30})
    void calculateWeekendDiscount_OnWeekend_ProvidesCorrectDiscount(int weekend) {
        Order mockOrder = mock(Order.class);
        when(mockOrder.countTotalQuantityForCategory(Menu.Category.MAIN)).thenReturn(2);

        assertThat(discountCalculator.calculateWeekendDiscount(LocalDate.of(2023, 12, weekend), mockOrder))
                .isEqualTo(2 * WEEKEND_DISCOUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {25, 31})
    void calculateSpecialDiscount_OnSpecialDays_ProvidesDiscount(int starDay) {
        assertThat(discountCalculator.calculateSpecialDiscount(LocalDate.of(2023, 12, starDay), STAR_DAYS))
                .isEqualTo(1000);
    }

    @Test
    void calculateSpecialDiscount_NotOnSpecialDays_NoDiscount() {
        assertThat(discountCalculator.calculateSpecialDiscount(
                LocalDate.of(2023, 12, 1), STAR_DAYS
        ))
                .isZero();
    }
}
