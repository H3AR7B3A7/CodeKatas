package com.gildedrose.storeItems;

import com.gildedrose.Item;
import com.gildedrose.StoreItem;

public class BaseStoreItem extends Item implements StoreItem {

    public BaseStoreItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public int getSellin() {
        return sellIn;
    }

    @Override
    public void updateItem() {
        changeQuality(getExpiryRate());
        sellIn = sellIn - 1;
    }

    @Override
    public void changeQuality(int delta) {
        int updatedQuality = quality + delta;
        quality = isOverMaxQuality(updatedQuality) ?
            MAX_QUALITY : isUnderMinQuality(updatedQuality) ?
            MIN_QUALITY : updatedQuality;
    }
}
