package com.gildedrose.storeItems;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum StoreItemFactory {
    BaseStoreItem(null) {
        @Override
        StoreItem create(String name, int sellin, int quality, boolean conjured) {
            return new BaseStoreItem(name, sellin, quality, conjured);
        }
    },
    Brie("Aged Brie") {
        @Override
        StoreItem create(String name, int sellin, int quality, boolean conjured) {
            return new Brie(name, sellin, quality, conjured);
        }
    },
    BackStagePasses("Backstage passes to a TAFKAL80ETC concert") {
        @Override
        StoreItem create(String name, int sellin, int quality, boolean conjured) {
            return new BackStagePasses(name, sellin, quality, conjured);
        }
    },
    Sulfuras("Sulfuras, Hand of Ragnaros") {
        @Override
        StoreItem create(String name, int sellin, int quality, boolean conjured) {
            return new Sulfuras(name, sellin, quality, conjured);
        }
    };

    private static final Map<String, StoreItemFactory> nameToEnum =
        Stream.of(values()).collect(
            toMap(Object::toString, e -> e));

    public final String name;

    StoreItemFactory(String name) {
        this.name = name;
    }

    private static StoreItemFactory fromString(String name) {
        return nameToEnum.getOrDefault(name, BaseStoreItem);
    }

    abstract StoreItem create(String name, int sellin, int quality, boolean conjured);

    public static StoreItem get(String name, int sellin, int quality) {
        boolean conjured = name.startsWith("Conjured ");
        String baseName = name.replaceAll("^(Conjured )", "");
        return fromString(baseName).create(baseName, sellin, quality, conjured);
    }

    @Override
    public String toString() {
        return name;
    }
}
