package christmas.domain;

import static christmas.util.ErrorMessages.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Menu.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order("양송이수프-2,레드와인-1");
    }

    @Test
    void addMenuItem_ValidMenuAndQuantity_DoesNotThrowException() {
        assertThatCode(() -> order.addMenuItem(Menu.CAESAR_SALAD, 1))
                .doesNotThrowAnyException();
    }

    @Test
    void addMenuItem_ExistingMenu_ThrowsException() {
        assertThatThrownBy(() -> order.addMenuItem(Menu.MUSHROOM_SOUP, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-abc", "양송이수프--1"})
    void parseQuantity_InvalidFormat_ThrowsException(String invalidOrder) {
        assertThatThrownBy(() -> new Order(invalidOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    void parseQuantity_ZeroQuantity_ThrowsException() {
        String zeroQuantityOrder = "양송이수프-0";
        assertThatThrownBy(() -> new Order(zeroQuantityOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-1, 아이스크림-17", "양송이수프-21"})
    void validateTotalQuantityMaximum_TooManyItems_ThrowsException(String largeOrder) {
        assertThatThrownBy(() -> new Order(largeOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    void validateContainsNonBeverage_OnlyBeverages_ThrowsException() {
        String beveragesOnly = "제로콜라-2,레드와인-1";
        assertThatThrownBy(() -> new Order(beveragesOnly))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    void countTotalQuantityForCategory_WithValidCategory_ReturnsCorrectQuantity() {
        assertThat(order.countTotalQuantityForCategory(Category.APPETIZER)).isEqualTo(2);
    }

    @Test
    void countTotalQuantityForCategory_WithNoItemsOfCategory_ReturnsZero() {
        assertThat(order.countTotalQuantityForCategory(Menu.Category.MAIN)).isZero();
    }

    @Test
    void calculateTotalAmount_WithMultipleItems_ReturnsCorrectTotal() {
        order = new Order("양송이수프-2,바비큐립-1,아이스크림-3");

        int expectedTotal = (6000 * 2) + (54000 * 1) + (5000 * 3);
        assertThat(order.calculateTotalAmount()).isEqualTo(expectedTotal);
    }
}
