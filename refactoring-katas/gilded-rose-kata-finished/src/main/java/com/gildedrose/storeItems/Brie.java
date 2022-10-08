package com.gildedrose.storeItems;

import com.gildedrose.StoreItem;

public class Brie extends BaseStoreItem implements StoreItem {
    public Brie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        int qualityIncrease = isExpired() ? 2 : 1;
        changeQuality(qualityIncrease);
        sellIn = sellIn - 1;
    }
}
