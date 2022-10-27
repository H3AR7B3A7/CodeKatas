package com.gildedrose.storeItems;

import com.gildedrose.Item;

import java.util.Objects;

class SimpleStoreItem extends AbstractStoreItem {

    private boolean conjured;

    public SimpleStoreItem(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality);
        this.conjured = conjured;
    }

    @Override
    public boolean isConjured() {
        return conjured;
    }

    @Override
    public void setConjured(boolean conjured) {
        this.conjured = conjured;
    }

    @Override
    public String getFullItemName() {
        String prefix = this.conjured ? "Conjured " : "";
        return prefix + this.name;
    }

    @Override
    public Item toItem() {
        return new Item(getFullItemName(), this.sellIn, this.quality);
    }

    @Override
    public String toString() {
        return "{" + getFullItemName() + ", " + this.sellIn + ", " + this.quality + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleStoreItem that = (SimpleStoreItem) o;
        return name.equals(that.name) &&
            sellIn == that.sellIn &&
            quality == that.quality &&
            conjured == that.conjured;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIn, quality, conjured);
    }
}
