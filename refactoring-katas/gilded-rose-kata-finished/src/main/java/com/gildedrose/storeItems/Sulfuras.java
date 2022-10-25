package com.gildedrose.storeItems;

public class Sulfuras extends BaseStoreItem {

    public Sulfuras(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality, conjured);
    }

    /**
     * Sulfuras does not lose quality or have sellin
     */
    @Override
    public void updateItem() {
        // Do nothing
    }
}
