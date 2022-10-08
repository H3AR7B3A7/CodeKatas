package com.gildedrose;

import com.gildedrose.storeItems.StoreItemFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class GildedRose {
    private final List<StoreItem> items;

    public GildedRose(Item... items) {
        this.items = Arrays.stream(items)
            .map(item -> StoreItemFactory.get(item.name, item.sellIn, item.quality))
            .collect(Collectors.toList());
    }

    public Item[] getItems() {
        return items.toArray(new Item[0]);
    }

    public void updateQuality() {
        for (StoreItem item : items) {
            item.updateItem();
        }
    }

    public void setConjured(int position, boolean conjured) {
        items.get(position).setConjured(conjured);
    }

    public void setAllCojured(boolean conjured) {
        items.forEach(item -> item.setConjured(conjured));
    }
}
