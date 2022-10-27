package com.gildedrose.storeItems;

import com.gildedrose.Item;

public interface StoreItem {

    void updateItem();

    int getSellin();

    String getFullItemName();

    boolean isConjured();

    void setConjured(boolean conjured);

    default int getDefaultExpiryRate() {
        int expiryRate = isExpired() ? -2 : -1;
        return isConjured() ? expiryRate * 2 : expiryRate;
    }

    boolean isExpired();

    boolean isOverMaxQuality(int quality);

    boolean isUnderMinQuality(int quality);

    Item toItem();
}
