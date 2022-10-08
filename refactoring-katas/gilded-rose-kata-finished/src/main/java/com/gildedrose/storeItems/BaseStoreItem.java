package com.gildedrose.storeItems;

import com.gildedrose.Item;
import com.gildedrose.StoreItem;

public class BaseStoreItem extends Item implements StoreItem {

    private boolean conjured;

    public BaseStoreItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        this.conjured = false;
    }

    public BaseStoreItem(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality);
        this.conjured = conjured;
    }

    @Override
    public int getSellin() {
        return sellIn;
    }

    @Override
    public void setConjured(boolean conjured) {
        this.conjured = conjured;
    }

    @Override
    public void updateItem() {
        int expiryRate = conjured ? getExpiryRate() * 2 : getExpiryRate();
        changeQuality(expiryRate);
        sellIn = sellIn - 1;
    }

    @Override
    public void changeQuality(int delta) {
        int updatedQuality = quality + delta;
        quality =
            isOverMaxQuality(updatedQuality) ? MAX_QUALITY :
                isUnderMinQuality(updatedQuality) ? MIN_QUALITY :
                    updatedQuality;
    }
}
