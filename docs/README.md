# 기능 목록

### 입력 기능

- [x] 날짜 입력 및 검증
    - [x] 사용자에게 12월 중 예상 방문 날짜를 숫자로 입력받음.
    - [x] 유효하지 않은 날짜 입력 시 "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." 메시지 출력.
        - [x] 입력된 날짜가 1 이상 31 이하인지 확인.
- [x] 메뉴 주문 입력 및 검증
    - [x] 사용자에게 주문할 메뉴와 개수를 문자열 형태로 입력받음 (e.g. 해산물파스타-2,레드와인-1,초코케이크-1).
    - [x] 유효하지 않은 주문 입력 시 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." 메시지 출력.
        - [x] 메뉴판에 있는 메뉴인지 확인.
        - [x] 각 메뉴의 개수가 1 이상인지 확인.
        - [x] 형식이 예시와 같은지 확인.
        - [x] 중복 메뉴가 없는지 확인.
        - [x] 각 메뉴의 합계가 20개 이하인지 확인.
        - [x] 음료 외의 메뉴가 포함되어 있는지 확인.

### 할인 및 증정 계산

- [x] 크리스마스 디데이 할인
    - [x] 방문 날짜에 따라 할인 금액을 계산 (1일 1,000원부터 25일 3,400원까지 매일 100원씩 증가).
- [x] 평일 할인(일~목)
    - [x] 주문된 디저트 메뉴에 대해 평일에는 메뉴당 2,023원 할인 적용.
- [x] 주말 할인(금, 토)
    - [x] 주문된 메인 메뉴에 대해 주말에는 메뉴당 2,023원 할인 적용.
- [x] 특별 할인
    - [x] 이벤트 달력에 별 표시된 날짜에 추가로 1,000원 할인 적용.
    - [x] 별 표시된 날짜: 3,10,17,24,25,31
- [x] 증정 이벤트
    - [x] 할인 전 총주문 금액이 12만 원 이상일 때 샴페인 1개 증정.
- [x] 이벤트 최소 금액
    - [x] 10,000원 미만에는 이벤트가 적용되지 않으며 해당 항목에 "없음" 또는 "0원"이라 출력.

### 결과 출력

- [x] 주문 메뉴 출력
    - [x] 사용자가 주문한 메뉴와 개수를 출력.
- [x] 할인 전 총주문 금액
    - [x] 사용자의 주문에 대한 할인 적용 전 총 금액 계산 및 출력.
- [x] 증정 메뉴
    - [x] 증정 이벤트 조건 충족 시 "샴페인-1개" 출력.
    - [x] 증정 조건 미충족 시 "없음" 출력.
- [x] 혜택 내역
    - [x] 적용된 모든 할인과 증정 내역 출력.
    - [x] 할인 또는 증정이 없는 경우 "없음" 출력.
- [x] 총혜택 금액
    - [x] 적용된 할인 및 증정 금액 합계를 총혜택 금액으로 계산 및 출력.
- [x] 할인 후 예상 결제 금액
    - [x] 할인 후 고객이 지불할 예상 금액 계산 및 출력.
- [x] 이벤트 배지
    - [x] 총혜택 금액에 따라 이벤트 배지 (별, 트리, 산타) 부여 및 출력.
