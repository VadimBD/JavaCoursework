package org.example.onlinestore.enums;

public enum Gender {
    MALE(0), FEMALE(1), OTHER(2);

    private final int code;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Gender fromCode(int code) {
        for (Gender g : values()) {
            if (g.getCode() == code) return g;
        }
        throw new IllegalArgumentException("Invalid Gender code: " + code);
    }
}