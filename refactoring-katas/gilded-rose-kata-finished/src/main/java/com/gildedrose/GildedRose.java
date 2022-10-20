package com.gildedrose;

import com.gildedrose.storeItems.StoreItem;
import com.gildedrose.storeItems.StoreItemFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class GildedRose {
    private final List<StoreItem> items;

    public GildedRose(Item... items) {
        this.items = items == null ? Collections.emptyList() :
            Arrays.stream(items)
                .filter(Objects::nonNull)
                .map(item -> StoreItemFactory.get(item.name, item.sellIn, item.quality))
                .collect(Collectors.toList());
    }

    public Item[] getItems() {
        return items.stream().map(StoreItem::toItem).toArray(Item[]::new);
    }

    public void updateQuality() {
        for (StoreItem item : items) {
            item.updateItem();
        }
    }

    public void setConjured(int position, boolean conjured) {
        items.get(position).setConjured(conjured);
    }

    public void setAllConjured(boolean conjured) {
        items.forEach(item -> item.setConjured(conjured));
    }
}
