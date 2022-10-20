package com.gildedrose.storeItems;

public class Sulfuras extends BaseStoreItem implements StoreItem {

    public Sulfuras(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality, conjured);
    }

    @Override
    public void updateItem() {
        // Does not lose quality or have sellin
    }
}
