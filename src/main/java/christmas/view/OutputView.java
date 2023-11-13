package christmas.view;

public class OutputView {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }

    public void printException(final Exception exception) {
        System.out.println(ERROR_PREFIX + exception.getMessage());
    }
}
