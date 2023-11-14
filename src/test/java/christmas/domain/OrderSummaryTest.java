package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderSummaryTest {
    private Order order;
    private DiscountCalculator discountCalculator;
    private OrderSummary orderSummary;
    private LocalDate testDate;
    private Set<LocalDate> specialDays;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        discountCalculator = mock(DiscountCalculator.class);
        orderSummary = new OrderSummary(order, discountCalculator);
        testDate = LocalDate.now();
        specialDays = new HashSet<>();
    }

    @Test
    void testTotalOrderAmount() {
        when(order.calculateTotalAmount()).thenReturn(12000);
        String summary = orderSummary.generateSummary(testDate, specialDays);
        assertTrue(summary.contains("<할인 전 총주문 금액>\n12,000원"));
    }

    @Test
    void testDiscountCalculationForEachType() {
        when(order.calculateTotalAmount()).thenReturn(15000);
        when(discountCalculator.calculateDDayDiscount(testDate)).thenReturn(1000);
        when(discountCalculator.calculateWeekdayDiscount(testDate, order)).thenReturn(500);
        when(discountCalculator.calculateWeekendDiscount(testDate, order)).thenReturn(300);
        when(discountCalculator.calculateSpecialDiscount(testDate, specialDays)).thenReturn(200);

        String summary = orderSummary.generateSummary(testDate, specialDays);
        assertTrue(summary.contains("크리스마스 디데이 할인: -1,000원"));
        assertTrue(summary.contains("평일 할인: -500원"));
        assertTrue(summary.contains("주말 할인: -300원"));
        assertTrue(summary.contains("특별 할인: -200원"));
    }

    @Test
    void testGiftEvent() {
        when(order.calculateTotalAmount()).thenReturn(15000);
        Menu giftMenu = Menu.fromName("샴페인");
        when(discountCalculator.calculateGiftEvent(order)).thenReturn(Optional.of(giftMenu));

        String summary = orderSummary.generateSummary(testDate, specialDays);
        assertTrue(summary.contains("<증정 메뉴>\n샴페인 1개"));
    }

    @Test
    void testFinalAmountAfterDiscounts() {
        when(order.calculateTotalAmount()).thenReturn(20000);
        when(discountCalculator.calculateDDayDiscount(testDate)).thenReturn(1000);
        when(discountCalculator.calculateWeekdayDiscount(testDate, order)).thenReturn(500);

        String summary = orderSummary.generateSummary(testDate, specialDays);
        assertTrue(summary.contains("<할인 후 예상 결제 금액>\n18,500원"));
    }

    @Test
    void testEventBadgeAssignment() {
        when(order.calculateTotalAmount()).thenReturn(10000);
        when(discountCalculator.calculateSpecialDiscount(testDate, specialDays)).thenReturn(500);

        String summary = orderSummary.generateSummary(testDate, specialDays);
        assertTrue(summary.contains("<12월 이벤트 배지>\n트리"));
    }
}
