package com.gildedrose;

public interface StoreItem {
    Integer MAX_QUALITY = 50;
    Integer MIN_QUALITY = 0;
    Integer EXPIRED = 0;

    void updateItem();

    void changeQuality(int delta);

    int getSellin();

    default int getExpiryRate() {
        int baseRate = isExpired() ? -2 : -1;
        return isConjured() ? baseRate * 2 : baseRate;
    }

    default boolean isExpired() {
        return getSellin() <= EXPIRED;
    }

    default boolean isOverMaxQuality(int quality) {
        return quality > MAX_QUALITY;
    }

    default boolean isUnderMinQuality(int quality) {
        return quality < MIN_QUALITY;
    }

    default boolean isConjured() {
        return false;
    }
}
