package christmas.view;

import java.time.LocalDate;

public class OutputView {
    public static final String ERROR_PREFIX = "[ERROR] ";
    public static final String RETRY = " 다시 입력해 주세요.";

    public void print(String result) {
        System.out.print(result);
    }

    public void printException(final Exception exception) {
        System.out.println(ERROR_PREFIX + exception.getMessage() + RETRY);
    }

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreview(LocalDate date) {
        System.out.println(
                date.getMonthValue() + "월 " + date.getDayOfMonth() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
}
