package com.gildedrose;

import java.util.Arrays;

public class TextTestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 10;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : app.getItems()) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }

        /**
         * API play with conjured special items
         */
        Item[] brie = new Item[] {
            new Item("Aged Brie", 5, 5),
            new Item("Conjured Aged Brie", 5, 5) };

        GildedRose cheeseShop = new GildedRose(brie);

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : cheeseShop.getItems()) {
                System.out.println(item);
            }
            System.out.println();
            cheeseShop.updateQuality();
        }

        /**
         * API play with conjured item shops convenience methods,
         * and conjured item name mapping
         */
        System.out.println("-------- Conjure store items --------");
        Item[] regularItems = new Item[] {
            new Item("Robe", 5, 5),
            new Item("Staff", 5, 5) };

        GildedRose mageShop = new GildedRose(regularItems);

        mageShop.setAllConjured(true);
        Arrays.stream(mageShop.getItems()).forEach(System.out::println);
        mageShop.setAllConjured(false);
        Arrays.stream(mageShop.getItems()).forEach(System.out::println);
        mageShop.setConjured(0, true);
        Arrays.stream(mageShop.getItems()).forEach(System.out::println);

        /**
         * API play with sorted store items
         */
        System.out.println("-------- Sorted store --------");
        Arrays.stream(app.getItems(GildedRose.NATURAL_ORDER)).forEach(System.out::println);
    }
}
