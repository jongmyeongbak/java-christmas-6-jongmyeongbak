package christmas.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

public class OrderSummary {
    private static final String CURRENCY = "원";
    private static final int EVENT_THRESHOLD_AMOUNT = 10000;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final Order order;
    private final DiscountCalculator discountCalculator;

    public OrderSummary(final Order order, final DiscountCalculator discountCalculator) {
        this.order = order;
        this.discountCalculator = discountCalculator;
    }

    public String generateSummary(final LocalDate date, final Set<LocalDate> specialDays) {
        final int totalAmount = order.calculateTotalAmount();
        final boolean isEligibleForEvent = totalAmount >= EVENT_THRESHOLD_AMOUNT;

        final Map<DiscountType, Integer> discounts = calculateDiscounts(date, specialDays, isEligibleForEvent);
        final Optional<Menu> gift = calculateGiftEvent(isEligibleForEvent);

        return buildEventPreview(totalAmount, discounts, gift);
    }

    private Map<DiscountType, Integer> calculateDiscounts(final LocalDate date, final Set<LocalDate> specialDays,
                                                          boolean isEligibleForEvent) {
        if (!isEligibleForEvent) {
            return Collections.emptyMap();
        }
        Map<DiscountType, Integer> discounts = new EnumMap<>(DiscountType.class);
        for (DiscountType type : DiscountType.values()) {
            int discount = type.calculateDiscount(discountCalculator, order, date, specialDays);
            discounts.put(type, discount);
        }
        return discounts;
    }

    private Optional<Menu> calculateGiftEvent(final boolean isEligibleForEvent) {
        if (isEligibleForEvent) {
            return discountCalculator.calculateGiftEvent(order);
        }
        return Optional.empty();
    }


    private String buildEventPreview(final int totalAmount, final Map<DiscountType, Integer> discounts,
                                     final Optional<Menu> gift) {
        final StringJoiner benefitDetailMenu = makeBenefitDetail(discounts, gift);

        final int totalDiscount = getTotalDiscount(discounts);
        final int finalAmount = totalAmount - totalDiscount;
        final int totalBenefit = totalDiscount + gift.map(Menu::getPrice).orElse(0);
        final StringBuilder sb = new StringBuilder();
        appendOrderMenu(sb);
        appendMenu(sb, "할인 전 총주문 금액", totalAmount);
        appendGiftMenu(sb, gift);
        sb.append(LINE_SEPARATOR).append(LINE_SEPARATOR).append("<혜택 내역>").append(LINE_SEPARATOR)
                .append(benefitDetailMenu);
        appendMenu(sb, "총혜택 금액", -totalBenefit);
        appendMenu(sb, "할인 후 예상 결제 금액", finalAmount);
        appendEventBadge(sb, totalBenefit);
        return sb.toString();
    }

    private StringJoiner makeBenefitDetail(final Map<DiscountType, Integer> discounts, final Optional<Menu> gift) {
        StringJoiner benefitDetail = new StringJoiner(LINE_SEPARATOR);
        discounts.forEach((type, amount) ->
                addDiscountContent(benefitDetail, type.getDisplayName(), amount)
        );
        addDiscountContent(benefitDetail, "증정 이벤트", gift.map(Menu::getPrice).orElse(0));

        if (benefitDetail.length() == 0) {
            benefitDetail.add("없음");
        }
        return benefitDetail;
    }

    private void addDiscountContent(final StringJoiner sj, final String discountName, final int discountAmount) {
        if (discountAmount > 0) {
            sj.add(String.format("%s: %,d%s", discountName, -discountAmount, CURRENCY));
        }
    }


    private int getTotalDiscount(final Map<DiscountType, Integer> discounts) {
        return discounts.values().stream().mapToInt(Integer::intValue).sum();
    }

    private void appendOrderMenu(final StringBuilder sb) {
        sb.append(LINE_SEPARATOR).append("<주문 메뉴>");
        order.getOrderItems()
                .forEach((menu, quantity) -> sb.append(LINE_SEPARATOR)
                        .append(menu.getName()).append(' ').append(quantity).append("개"));
    }

    private void appendMenu(final StringBuilder sb, final String menuName, final int amount) {
        String formattedAmount = String.format("%,d", amount);
        sb.append(LINE_SEPARATOR).append(LINE_SEPARATOR).append('<').append(menuName).append('>')
                .append(LINE_SEPARATOR).append(formattedAmount).append(CURRENCY);
    }

    private void appendGiftMenu(final StringBuilder sb, final Optional<Menu> gift) {
        sb.append(LINE_SEPARATOR).append(LINE_SEPARATOR).append("<증정 메뉴>").append(LINE_SEPARATOR);
        if (gift.isEmpty()) {
            sb.append("없음");
            return;
        }
        final Menu menu = gift.get();
        sb.append(menu.getName()).append(" 1개");
    }

    private void appendEventBadge(final StringBuilder sb, final int totalBenefit) {
        sb.append(LINE_SEPARATOR).append(LINE_SEPARATOR).append("<12월 이벤트 배지>").append(LINE_SEPARATOR);
        String badge = "없음";

        if (totalBenefit >= 20000) {
            badge = "산타";
        } else if (totalBenefit >= 10000) {
            badge = "트리";
        } else if (totalBenefit >= 5000) {
            badge = "별";
        }

        sb.append(badge);
    }
}
