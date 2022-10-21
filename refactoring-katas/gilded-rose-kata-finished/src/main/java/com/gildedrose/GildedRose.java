package com.gildedrose;

import com.gildedrose.storeItems.StoreItem;
import com.gildedrose.storeItems.StoreItemFactory;

import java.util.*;
import java.util.stream.Collectors;

public final class GildedRose {

    public static final Comparator<Item> NATURAL_ORDER = Comparator
        .comparing((Item p) -> p.name)
        .thenComparingInt(p -> p.quality)
        .thenComparingInt(p -> p.sellIn);

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

    public Item[] getItems(Comparator<Item> comparator) {
        return items.stream().map(StoreItem::toItem).sorted(comparator).toArray(Item[]::new);
    }

    public void updateQuality() {
        for (StoreItem item : items) {
            item.updateItem();
        }
    }

    public void setConjuredForItemAt(int position, boolean conjured) {
        items.get(position).setConjured(conjured);
    }

    public void setConjuredForAllItemsInStore(boolean conjured) {
        items.forEach(item -> item.setConjured(conjured));
    }
}
