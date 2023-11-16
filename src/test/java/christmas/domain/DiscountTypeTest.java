package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiscountTypeTest {
    private DiscountCalculator discountCalculator;
    private Order order;
    private LocalDate testDate;
    private Set<LocalDate> specialDays;

    @BeforeEach
    void setUp() {
        discountCalculator = mock(DiscountCalculator.class);
        order = mock(Order.class);
        testDate = LocalDate.now();
        specialDays = new HashSet<>();
    }

    @Test
    void testDDayDiscountCalculation() {
        when(discountCalculator.calculateDDayDiscount(testDate)).thenReturn(1000);
        int discount = DiscountType.D_DAY.calculateDiscount(discountCalculator, order, testDate, specialDays);
        assertEquals(1000, discount);
    }

    @Test
    void testWeekdayDiscountCalculation() {
        when(discountCalculator.calculateWeekdayDiscount(testDate, order)).thenReturn(500);
        int discount = DiscountType.WEEKDAY.calculateDiscount(discountCalculator, order, testDate, specialDays);
        assertEquals(500, discount);
    }

    @Test
    void testWeekendDiscountCalculation() {
        when(discountCalculator.calculateWeekendDiscount(testDate, order)).thenReturn(300);
        int discount = DiscountType.WEEKEND.calculateDiscount(discountCalculator, order, testDate, specialDays);
        assertEquals(300, discount);
    }

    @Test
    void testSpecialDiscountCalculation() {
        when(discountCalculator.calculateSpecialDiscount(testDate, specialDays)).thenReturn(200);
        int discount = DiscountType.SPECIAL.calculateDiscount(discountCalculator, order, testDate, specialDays);
        assertEquals(200, discount);
    }
}
