package christmas.domain;

import static christmas.util.ErrorMessages.INVALID_ORDER;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Order {
    private static final int TOTAL_QUANTITY_MAXIMUM = 20;
    private final Map<Menu, Integer> orderItems;

    public Order(final String orderInput) {
        orderItems = new EnumMap<>(Menu.class);
        parseOrder(orderInput);
    }

    private void parseOrder(final String orderInput) {
        String[] items = orderInput.split(",");
        int totalQuantity = 0;

        for (String item : items) {
            String[] parts = splitItemByTwoParts(item);
            Menu menu = Menu.fromName(parts[0]);
            int quantity = parseQuantity(parts[1]);
            addMenuItem(menu, quantity);

            totalQuantity += quantity;
        }
        validateTotalQuantityMaximum(totalQuantity);
        validateContainsNonBeverage();
    }

    private String[] splitItemByTwoParts(final String item) {
        String[] parts = item.split("-");
        validatePartsOfTwo(parts);
        return parts;
    }

    private void validatePartsOfTwo(final String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private int parseQuantity(final String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    public void addMenuItem(final Menu menu, final int quantity) {
        validateNotExistMenu(menu);
        validatePositiveQuantity(quantity);
        orderItems.put(menu, quantity);
    }

    private void validatePositiveQuantity(final int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private void validateNotExistMenu(final Menu menu) {
        if (orderItems.get(menu) != null) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private void validateTotalQuantityMaximum(final int totalQuantity) {
        if (totalQuantity > TOTAL_QUANTITY_MAXIMUM) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private void validateContainsNonBeverage() {
        boolean nonBeverageExists = orderItems.keySet().stream().anyMatch(Menu::isNonBeverage);
        if (!nonBeverageExists) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    public int countTotalQuantityForCategory(final Menu.Category category) {
        return orderItems.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int calculateTotalAmount() {
        return orderItems.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<Menu, Integer> getOrderItems() {
        return Collections.unmodifiableMap(orderItems);
    }
}
