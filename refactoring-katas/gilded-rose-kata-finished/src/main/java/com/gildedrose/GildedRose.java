package com.gildedrose;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.gildedrose.ItemType.AGED_BRIE;
import static com.gildedrose.ItemType.BACKSTAGE_PASSES;
import static com.gildedrose.ItemType.CONJURED_ITEM;
import static com.gildedrose.ItemType.SULFURAS;

public final class GildedRose {
    private final List<Item> items;
    private static final Integer MAX_QUALITY = 50;
    private static final Integer MIN_QUALITY = 0;
    private static final Integer NEAR_EXPIRY = 10;
    private static final Integer VERY_NEAR_EXPIRY = 5;
    private static final Integer EXPIRED = 0;

    private GildedRose(Item[] items) {
        this.items = List.of(items);
    }

    public static GildedRose createStoreWith(Item... items) {
        return new GildedRose(items);
    }

    public List<Item> getInventory() {
        return new ArrayList<>(items);
    }

    public void updateAtEndOfDay() {
        for (Item item : items) {
            updateQuality(item);
        }
    }

    private void updateQuality(Item item) {
        if (isPerishable(item)) {
            changeQuality(item, getExpiryRate(item));
        }

        if (item.name.equals(AGED_BRIE.getName())) {
            int qualityIncrease = isExpired(item) ? 2 : 1;
            changeQuality(item, qualityIncrease);
        }

        if (item.name.equals(BACKSTAGE_PASSES.getName())) {
            changeBackstagePassQuality(item);
        }

        if (hasSellIn(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void changeQuality(Item item, int delta) {
        int updatedQuality = item.quality + delta;
        item.quality = isOverMaxQuality(updatedQuality) ?
            MAX_QUALITY : isUnderMinQuality(updatedQuality) ?
            MIN_QUALITY : updatedQuality;
    }

    private void changeBackstagePassQuality(Item item) {
        int qualityIncrease = isVeryNearEventDate(item) ?
            3 : isNearEventDate(item) ?
            2 : isExpired(item) ?
            item.quality = MIN_QUALITY : 1;
        changeQuality(item, qualityIncrease);
    }

    private boolean isPerishable(Item item) {
        return Stream.of(AGED_BRIE.getName(), BACKSTAGE_PASSES.getName(), SULFURAS.getName())
            .noneMatch(specialItem -> specialItem.equals(item.name));
    }

    private int getExpiryRate(Item item) {
        int baseRate = isExpired(item) ? -2 : -1;
        return isConjured(item) ? baseRate * 2 : baseRate;
    }

    private boolean hasSellIn(Item item) {
        return !item.name.equals(SULFURAS.getName());
    }

    private boolean isNearEventDate(Item backstagePasses) {
        return backstagePasses.sellIn <= NEAR_EXPIRY && backstagePasses.sellIn > VERY_NEAR_EXPIRY;
    }

    private boolean isVeryNearEventDate(Item backstagePasses) {
        return backstagePasses.sellIn <= VERY_NEAR_EXPIRY && backstagePasses.sellIn > 0;
    }

    private boolean isExpired(Item item) {
        return item.sellIn <= EXPIRED;
    }

    private boolean isOverMaxQuality(int quality) {
        return quality > MAX_QUALITY;
    }

    private boolean isUnderMinQuality(int quality) {
        return quality < MIN_QUALITY;
    }

    private boolean isConjured(Item item) {
        return item.name.equals(CONJURED_ITEM.getName());
    }
}
