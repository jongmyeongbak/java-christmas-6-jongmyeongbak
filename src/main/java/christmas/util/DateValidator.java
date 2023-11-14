package christmas.util;

import static christmas.util.ErrorMessages.INVALID_DATE;

public class DateValidator {
    private DateValidator() {
    }

    public static void validateDecemberDateRange(final int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }
}
