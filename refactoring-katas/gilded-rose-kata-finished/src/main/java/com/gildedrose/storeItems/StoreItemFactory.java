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
        public StoreItem create(String name, int sellin, int quality) {
            return new Brie(name, sellin, quality);
        }
    },
    BackStagePasses {
        @Override
        public StoreItem create(String name, int sellin, int quality) {
            return new BackStagePasses(name, sellin, quality);
        }
    },
    Sulfuras {
        @Override
        public StoreItem create(String name, int sellin, int quality) {
            return new Sulfuras(name, sellin, quality);
        }
    };

    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public abstract StoreItem create(String name, int sellin, int quality);

    public static StoreItem get(String name, int sellin, int quality) {
        return switch (name) {
            case AGED_BRIE -> Brie.create(name, sellin, quality);
            case SULFURAS -> Sulfuras.create(name, sellin, quality);
            case BACKSTAGE_PASSES -> BackStagePasses.create(name, sellin, quality);
            default -> BaseStoreItem.create(name, sellin, quality);
        };
    }
}
