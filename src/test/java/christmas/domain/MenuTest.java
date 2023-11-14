package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void fromName_ValidName_ReturnsCorrectMenu() {
        assertThat(Menu.fromName("양송이수프")).isEqualTo(Menu.MUSHROOM_SOUP);
        assertThat(Menu.fromName("레드와인")).isEqualTo(Menu.RED_WINE);
    }

    @Test
    void fromName_InvalidName_ThrowsException() {
        assertThatThrownBy(() -> Menu.fromName("없는메뉴"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다.");
    }

    @Test
    void isNonBeverage_Beverage_ReturnsFalse() {
        assertThat(Menu.RED_WINE.isNonBeverage()).isFalse();
    }

    @Test
    void isNonBeverage_NonBeverage_ReturnsTrue() {
        assertThat(Menu.MUSHROOM_SOUP.isNonBeverage()).isTrue();
    }
}
