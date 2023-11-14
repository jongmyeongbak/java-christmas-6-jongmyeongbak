package christmas.view;

import static christmas.util.ErrorMessages.INVALID_DATE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return parseInt(Console.readLine());
    }

    public int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE, e);
        }
    }

    public String readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (예: 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine().trim();
    }

}
