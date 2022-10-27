package com.gildedrose.storeItems;

public class BackStagePasses extends SimpleStoreItem {

    private static final Integer NEAR_EXPIRY = 10;
    private static final Integer VERY_NEAR_EXPIRY = 5;

    public BackStagePasses(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality, conjured);
    }

    /**
     * Backstage passes quality depends solely on hype,
     * that is the number of days before expiry,
     * being conjured does not affect quality.
     * Sellin decreases by one.
     */
    @Override
    public void updateItem() {
        int qualityIncrease =
            isVeryNearEventDate() ? 3 :
                isNearEventDate() ? 2 :
                    isExpired() ? quality = 0 :
                        1;
        changeQuality(qualityIncrease);
        sellIn = sellIn - 1;
    }

    private boolean isNearEventDate() {
        return sellIn <= NEAR_EXPIRY && sellIn > VERY_NEAR_EXPIRY;
    }

    private boolean isVeryNearEventDate() {
        return sellIn <= VERY_NEAR_EXPIRY && sellIn > 0;
    }
}
