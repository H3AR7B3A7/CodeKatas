package com.gildedrose.storeItems;

import com.gildedrose.StoreItem;

public class Sulfuras extends BaseStoreItem implements StoreItem {

    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        // Does not lose quality or have sellin
    }
}
