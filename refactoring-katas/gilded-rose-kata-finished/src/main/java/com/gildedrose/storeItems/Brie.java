package com.gildedrose.storeItems;

public class Brie extends BaseStoreItem implements StoreItem {
    public Brie(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality, conjured);
    }

    @Override
    public void updateItem() {
        // TODO : Decide on the behavior of conjured brie
        changeQuality(Math.abs(getDefaultExpiryRate()));
        sellIn = sellIn - 1;
    }
}
