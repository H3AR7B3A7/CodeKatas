package com.gildedrose.storeItems;

public interface StoreItem {
    Integer MAX_QUALITY = 50;
    Integer MIN_QUALITY = 0;
    Integer EXPIRED = 0;

    void updateItem();

    int getSellin();

    boolean isConjured();

    void setConjured(boolean conjured);

    default int getDefaultExpiryRate() {
        int expiryRate = isExpired() ? -2 : -1;
        return isConjured() ? expiryRate * 2 : expiryRate;
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
}
