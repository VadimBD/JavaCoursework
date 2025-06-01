package org.example.onlinestore.enums;

public enum ShippingType {
    PICK_UP (1),
    NOVA_POSHTA (2);
    private final int value;
    ShippingType(int value)
    {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
