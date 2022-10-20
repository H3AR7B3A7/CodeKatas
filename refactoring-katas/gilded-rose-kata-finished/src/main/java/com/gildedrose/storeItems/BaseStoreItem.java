package com.gildedrose.storeItems;

import com.gildedrose.Item;

import java.util.Objects;

public class BaseStoreItem extends Item implements StoreItem {

    private boolean conjured;

    public BaseStoreItem(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality);
        this.conjured = conjured;
    }

    @Override
    public int getSellin() {
        return sellIn;
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
    public void updateItem() {
        changeQuality(getDefaultExpiryRate());
        sellIn = sellIn - 1;
    }

    void changeQuality(int delta) {
        int updatedQuality = quality + delta;
        quality =
            isOverMaxQuality(updatedQuality) ? MAX_QUALITY :
                isUnderMinQuality(updatedQuality) ? MIN_QUALITY :
                    updatedQuality;
    }

    public String getItemName() {
        String prefix = this.conjured ? "Conjured " : "";
        return prefix + this.name;
    }

    public Item toItem() {
        return new Item(getItemName(), this.sellIn, this.quality);
    }

    @Override
    public String toString() {
        return "{" + getItemName() + ", " + this.sellIn + ", " + this.quality + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseStoreItem that = (BaseStoreItem) o;
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
