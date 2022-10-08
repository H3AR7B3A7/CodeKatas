package com.gildedrose.storeItems;

import com.gildedrose.StoreItem;

public enum StoreItemFactory {
    BaseStoreItem {
        @Override
        public StoreItem create(String name, int sellin, int quality) {
            return new BaseStoreItem(name, sellin, quality);
        }
    },
    Brie {
        @Override
        public StoreItem create(int sellin, int quality) {
            return new Brie(AGED_BRIE, sellin, quality);
        }
    },
    BackStagePasses {
        @Override
        public StoreItem create(int sellin, int quality) {
            return new BackStagePasses(BACKSTAGE_PASSES, sellin, quality);
        }
    },
    Sulfuras {
        @Override
        public StoreItem create(int sellin, int quality) {
            return new Sulfuras(SULFURAS, sellin, quality);
        }
    };

    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public abstract StoreItem create(int sellin, int quality);

    public static StoreItem get(String name, int sellin, int quality) {
        return switch (name) {
            case AGED_BRIE -> Brie.create(sellin, quality);
            case SULFURAS -> Sulfuras.create(sellin, quality);
            case BACKSTAGE_PASSES -> BackStagePasses.create(sellin, quality);
            default -> BaseStoreItem.create(sellin, quality);
        };
    }
}
