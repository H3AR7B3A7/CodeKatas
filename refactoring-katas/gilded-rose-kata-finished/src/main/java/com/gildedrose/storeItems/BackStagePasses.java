package com.gildedrose.storeItems;

import com.gildedrose.StoreItem;

public class BackStagePasses extends BaseStoreItem implements StoreItem {

    private static final Integer NEAR_EXPIRY = 10;
    private static final Integer VERY_NEAR_EXPIRY = 5;

    public BackStagePasses(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        int qualityIncrease = isVeryNearEventDate() ?
            3 : isNearEventDate() ?
            2 : isExpired() ?
            quality = MIN_QUALITY : 1;
        changeQuality(qualityIncrease);
        sellIn = sellIn - 1;
        // TODO : Decide on the behavior of conjured backstage passes
    }

    private boolean isNearEventDate() {
        return sellIn <= NEAR_EXPIRY && sellIn > VERY_NEAR_EXPIRY;
    }

    private boolean isVeryNearEventDate() {
        return sellIn <= VERY_NEAR_EXPIRY && sellIn > 0;
    }
}
