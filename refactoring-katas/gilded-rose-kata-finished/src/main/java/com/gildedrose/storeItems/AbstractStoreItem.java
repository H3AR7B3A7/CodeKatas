package com.gildedrose.storeItems;

import com.gildedrose.Item;

abstract class AbstractStoreItem extends Item implements StoreItem {
    private static final Integer MAX_QUALITY = 50;
    private static final Integer MIN_QUALITY = 0;
    private static final Integer EXPIRED = 0;

    public AbstractStoreItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    /**
     * By default, an item decreases in quality by 1,
     * when past expiry by 2, and even double that when conjured.
     * Sellin decreases by one.
     */
    @Override
    public void updateItem() {
        changeQuality(getDefaultExpiryRate());
        sellIn = sellIn - 1;
    }

    void changeQuality(int delta) {
        int updatedQuality = quality + delta;
        quality =
            isOverMaxQuality(updatedQuality) ? MAX_QUALITY :
                isUnderMinQuality(updatedQuality) ? MIN_QUALITY :
                    updatedQuality;
    }

    @Override
    public int getSellin() {
        return sellIn;
    }

    public boolean isExpired() {
        return getSellin() <= EXPIRED;
    }

    public boolean isOverMaxQuality(int quality) {
        return quality > MAX_QUALITY;
    }

    public boolean isUnderMinQuality(int quality) {
        return quality < MIN_QUALITY;
    }
}
