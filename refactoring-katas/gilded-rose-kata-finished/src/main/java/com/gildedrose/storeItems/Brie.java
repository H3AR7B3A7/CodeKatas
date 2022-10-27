package com.gildedrose.storeItems;

public class Brie extends SimpleStoreItem {
    public Brie(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality, conjured);
    }

    /**
     * Brie 'increases' in quality by 1,
     * like regular items twice as much when spoiled
     * and even double that when conjured.
     * Sellin decreases by one.
     */
    @Override
    public void updateItem() {
        changeQuality(Math.abs(getDefaultExpiryRate()));
        sellIn = sellIn - 1;
    }
}
