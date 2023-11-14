package christmas.domain;

import java.time.LocalDate;

public class DiscountCalculator {
    private static final int D_DAY = 25;
    private static final int DISCOUNT_DAY1_AMOUNT = 1000;
    private static final int DAILY_INCREMENT = 100;

    public int calculateDdayDiscount(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth > D_DAY) {
            return 0;
        }
        return DISCOUNT_DAY1_AMOUNT + (dayOfMonth - 1) * DAILY_INCREMENT;
    }
}
