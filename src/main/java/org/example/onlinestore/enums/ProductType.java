package org.example.onlinestore.enums;

public enum ProductType {

    PARFUM(1),
    EAU_DE_PARFUM(2),
    EAU_DE_TOILETTE(3),
    EAU_DE_COLOGNE(4),
    TESTER(5),
    DEODORANT(6);

    private final int value;
    ProductType(int value)
    {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}