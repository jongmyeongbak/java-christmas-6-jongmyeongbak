package christmas.domain;

import java.time.LocalDate;
import java.util.Set;

public enum DiscountType {
    D_DAY("크리스마스 디데이 할인") {
        @Override
        public int calculateDiscount(final DiscountCalculator discountCalculator, final Order order,
                                     final LocalDate date, final Set<LocalDate> specialDays) {
            return discountCalculator.calculateDDayDiscount(date);
        }
    },
    WEEKDAY("평일 할인") {
        @Override
        public int calculateDiscount(final DiscountCalculator discountCalculator, final Order order,
                                     final LocalDate date, final Set<LocalDate> specialDays) {
            return discountCalculator.calculateWeekdayDiscount(date, order);
        }
    },
    WEEKEND("주말 할인") {
        @Override
        public int calculateDiscount(final DiscountCalculator discountCalculator, final Order order,
                                     final LocalDate date, final Set<LocalDate> specialDays) {
            return discountCalculator.calculateWeekendDiscount(date, order);
        }
    },
    SPECIAL("특별 할인") {
        @Override
        public int calculateDiscount(final DiscountCalculator discountCalculator, final Order order,
                                     final LocalDate date, final Set<LocalDate> specialDays) {
            return discountCalculator.calculateSpecialDiscount(date, specialDays);
        }
    };

    private final String displayName;

    DiscountType(final String displayName) {
        this.displayName = displayName;
    }

    public abstract int calculateDiscount(final DiscountCalculator discountCalculator, final Order order,
                                          final LocalDate date, final Set<LocalDate> specialDays);

    public String getDisplayName() {
        return displayName;
    }
}
