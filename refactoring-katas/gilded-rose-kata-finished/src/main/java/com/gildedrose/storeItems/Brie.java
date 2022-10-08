package com.gildedrose.storeItems;

import com.gildedrose.StoreItem;

public class Brie extends BaseStoreItem implements StoreItem {
    public Brie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        // TODO : Decide on the behavior of conjured brie
        changeQuality(Math.abs(getDefaultExpiryRate()));
        sellIn = sellIn - 1;
    }
}
