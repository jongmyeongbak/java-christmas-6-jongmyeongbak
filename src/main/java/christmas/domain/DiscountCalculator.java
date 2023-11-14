package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiscountCalculator {
    private static final int D_DAY = 25;
    private static final int DISCOUNT_DAY1_AMOUNT = 1000;
    private static final int DAILY_INCREMENT = 100;
    private static final int WEEKDAY_DISCOUNT_PER_MENU = 2023;
    private static final int WEEKEND_DISCOUNT_PER_MENU = 2023;

    public int calculateDDayDiscount(final LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth > D_DAY) {
            return 0;
        }
        return DISCOUNT_DAY1_AMOUNT + (dayOfMonth - 1) * DAILY_INCREMENT;
    }

    public int calculateWeekdayDiscount(final LocalDate date, final Order order) {
        if (isWeekday(date)) {
            return order.countTotalQuantityForCategory(Menu.Category.DESSERT) * WEEKDAY_DISCOUNT_PER_MENU;
        }
        return 0;
    }

    public int calculateWeekendDiscount(final LocalDate date, final Order order) {
        if (isWeekend(date)) {
            return order.countTotalQuantityForCategory(Menu.Category.MAIN) * WEEKEND_DISCOUNT_PER_MENU;
        }
        return 0;
    }

    private boolean isWeekday(final LocalDate date) {
        return !isWeekend(date);
    }

    private boolean isWeekend(final LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }
}
