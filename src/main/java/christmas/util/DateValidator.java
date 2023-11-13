package christmas.util;

public class DateValidator {
    private DateValidator() {
    }

    public static void validateDecemberDateRange(final int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다.");
        }
    }
}
