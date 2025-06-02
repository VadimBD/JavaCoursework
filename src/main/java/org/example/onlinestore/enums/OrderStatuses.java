package org.example.onlinestore.enums;

public enum OrderStatuses {
    NEW(1),
    SHIPPED(2),
    DELIVERED(3),
    COMPLETE(4);

    private final int value;

    OrderStatuses(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatuses fromValue(int value) {
        for (OrderStatuses status : values()) {
            if (status.value == value) return status;
        }
        throw new IllegalArgumentException("Unknown OrderStatus value: " + value);
    }
}
