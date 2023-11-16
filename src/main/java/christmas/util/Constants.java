package christmas.util;

import java.time.LocalDate;
import java.util.Set;

public class Constants {
    public static final Set<LocalDate> STAR_DAYS = Set.of(
            LocalDate.of(2023, 12, 3),
            LocalDate.of(2023, 12, 10),
            LocalDate.of(2023, 12, 17),
            LocalDate.of(2023, 12, 24),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31)
    );

    private Constants() {
    }
}
