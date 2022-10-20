package com.gildedrose.storeItems;

public enum StoreItemFactory {
    BaseStoreItem {
        @Override
        StoreItem create(String name, int sellin, int quality, boolean conjured) {
            return new BaseStoreItem(name, sellin, quality, conjured);
        }
    },
    Brie {
        @Override
        StoreItem create(String name, int sellin, int quality, boolean conjured) {
            return new Brie(name, sellin, quality, conjured);
        }
    },
    BackStagePasses {
        @Override
        StoreItem create(String name, int sellin, int quality, boolean conjured) {
            return new BackStagePasses(name, sellin, quality, conjured);
        }
    },
    Sulfuras {
        @Override
        StoreItem create(String name, int sellin, int quality, boolean conjured) {
            return new Sulfuras(name, sellin, quality, conjured);
        }
    };

    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    abstract StoreItem create(String name, int sellin, int quality, boolean conjured);

    public static StoreItem get(String name, int sellin, int quality) {
        boolean conjured = name.startsWith("Conjured ");
        String baseName = name.replaceAll("^(Conjured )", "");
        return switch (baseName) {
            case AGED_BRIE -> Brie.create(baseName, sellin, quality, conjured);
            case SULFURAS -> Sulfuras.create(baseName, sellin, quality, conjured);
            case BACKSTAGE_PASSES -> BackStagePasses.create(baseName, sellin, quality, conjured);
            default -> BaseStoreItem.create(baseName, sellin, quality, conjured);
        };
    }
}
