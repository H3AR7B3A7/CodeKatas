package com.gildedrose;

public interface StoreItem {
    Integer MAX_QUALITY = 50;
    Integer MIN_QUALITY = 0;
    Integer EXPIRED = 0;

    void updateItem();

    void changeQuality(int delta);

    int getSellin();

    void setConjured(boolean conjured);

    default int getExpiryRate() {
        return isExpired() ? -2 : -1;
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
