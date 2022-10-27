package com.gildedrose.storeItems;

class Sulfuras extends SimpleStoreItem {

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
