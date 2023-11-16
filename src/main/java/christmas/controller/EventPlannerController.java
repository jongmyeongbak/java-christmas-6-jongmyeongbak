package christmas.controller;

import static christmas.util.Constants.STAR_DAYS;

import christmas.domain.DiscountCalculator;
import christmas.domain.Order;
import christmas.domain.OrderSummary;
import christmas.util.DateValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;

public class EventPlannerController {
    InputView inputView;
    OutputView outputView;

    public EventPlannerController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        outputView.printWelcomeMessage();
        LocalDate date = getVisitDate();
        Order order = getOrderDetails();

        OrderSummary orderSummary = new OrderSummary(order, new DiscountCalculator());
        String result = orderSummary.generateSummary(date, STAR_DAYS);

        outputView.printEventPreview(date);
        outputView.print(result);
    }

    private LocalDate getVisitDate() {
        while (true) {
            try {
                int date = inputView.readDate();
                DateValidator.validateDecemberDateRange(date);
                return LocalDate.of(2023, 12, date);
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            }
        }
    }

    private Order getOrderDetails() {
        while (true) {
            try {
                String orderInput = inputView.readOrder();
                return new Order(orderInput);
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            }
        }
    }
}
