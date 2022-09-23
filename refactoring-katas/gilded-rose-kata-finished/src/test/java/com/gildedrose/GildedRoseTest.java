package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemType.AGED_BRIE;
import static com.gildedrose.ItemType.BACKSTAGE_PASSES;
import static com.gildedrose.ItemType.CONJURED_ITEM;
import static com.gildedrose.ItemType.SULFURAS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private static GildedRose app;

    @Nested
    @DisplayName("Given an array with an item")
    class Given {
        @Nested
        @DisplayName("When DAY ENDS")
        class When {
            @Test
            @DisplayName("The item name does not change")
            void foo() {
                Item[] items = new Item[]{new Item("foo", 0, 0)};
                GildedRose app = GildedRose.createStoreWith(items);

                app.updateAtEndOfDay();

                assertEquals("foo", app.getInventory().get(0).name);
            }
        }
    }

    @Nested
    @DisplayName("Given a regular item")
    class GivenRegularItem {
        @Nested
        @DisplayName("When NOT PAST SELLIN DATE")
        class When {
            @Test
            @DisplayName("Then the quality degrades by 1")
            void itemDegrades() {
                storeWith("Vest", 10, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(9);
                assertThat(quality()).isEqualTo(19);
            }
        }

        @Nested
        @DisplayName("When PAST SELLIN DATE")
        class WhenPastSellIn {
            @Test
            @DisplayName("Then the quality degrades by 2")
            void itemDegrades2() {
                storeWith("Vest", 0, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(-1);
                assertThat(quality()).isEqualTo(18);
            }
        }

        @Nested
        @DisplayName("When NO QUALITY")
        class WhenNoQuality {
            @Test
            @DisplayName("Then the quality remains 0")
            void itemQualityMinimum() {
                storeWith("Vest", 10, 0);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(9);
                assertThat(quality()).isEqualTo(0);
            }
        }
    }

    @Nested
    @DisplayName("Given aged brie")
    class GivenAgedBrie {
        @Nested
        @DisplayName("When NOT PAST SELLIN DATE")
        class When {
            @Test
            @DisplayName("Then the quality increases by 1")
            void itemDegrades() {
                storeWith(AGED_BRIE.getName(), 10, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(9);
                assertThat(quality()).isEqualTo(21);
            }
        }

        @Nested
        @DisplayName("When PAST SELLIN DATE")
        class WhenPastSellIn {
            @Test
            @DisplayName("Then the quality increases by 2")
            void itemDegrades2() {
                storeWith(AGED_BRIE.getName(), 0, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(-1);
                assertThat(quality()).isEqualTo(22);
            }
        }

        @Nested
        @DisplayName("When QUALITY = 50")
        class WhenOver50 {
            @Test
            @DisplayName("Then the quality stays the same")
            void itemQualityIsMaxed() {
                storeWith(AGED_BRIE.getName(), 0, 49);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(-1);
                assertThat(quality()).isEqualTo(50);
            }
        }
    }

    @Nested
    @DisplayName("Given sulfuras")
    class GivenSulfuras {
        @Nested
        @DisplayName("When NOT PAST SELLIN DATE")
        class When {
            @Test
            @DisplayName("The quality or sellIn never changes")
            void itemNeverDegrades() {
                storeWith(SULFURAS.getName(), 10, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(10);
                assertThat(quality()).isEqualTo(20);
            }
        }

        @Nested
        @DisplayName("When PAST SELLIN DATE")
        class WhenPastSellIn {
            @Test
            @DisplayName("The quality or sellIn never changes")
            void itemNeverDegrades() {
                storeWith(SULFURAS.getName(), 0, 0);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(0);
                assertThat(quality()).isEqualTo(0);
            }
        }
    }

    @Nested
    @DisplayName("Given backstage passes")
    class GivenBackstagePasses {
        @Nested
        @DisplayName("When SELLIN DATE > 10")
        class When {
            @Test
            @DisplayName("The quality increases by 1")
            void itemQualityIncreasesBy1() {
                storeWith(BACKSTAGE_PASSES.getName(), 11, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(10);
                assertThat(quality()).isEqualTo(21);
            }
        }

        @Nested
        @DisplayName("When SELLIN DATE <= 10")
        class WhenTenOrLess {
            @Test
            @DisplayName("The quality increases by 2")
            void itemQualityIncreasesBy2() {
                storeWith(BACKSTAGE_PASSES.getName(), 10, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(9);
                assertThat(quality()).isEqualTo(22);
            }
        }

        @Nested
        @DisplayName("When SELLIN DATE <= 5")
        class WhenFiveOrLess {
            @Test
            @DisplayName("The quality increases by 3")
            void itemQualityIncreasesBy3() {
                storeWith(BACKSTAGE_PASSES.getName(), 5, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(4);
                assertThat(quality()).isEqualTo(23);
            }
        }

        @Nested
        @DisplayName("When QUALITY = 50")
        class WhenOver50 {
            @Test
            @DisplayName("The quality remains the same")
            void itemQualityIsMaxed() {
                storeWith(BACKSTAGE_PASSES.getName(), 5, 49);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(4);
                assertThat(quality()).isEqualTo(50);
            }
        }

        @Nested
        @DisplayName("When PAST SELLIN DATE")
        class WhenPastSellIn {
            @Test
            @DisplayName("The quality drops to 0")
            void itemDegrades() {
                storeWith(BACKSTAGE_PASSES.getName(), 0, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(-1);
                assertThat(quality()).isEqualTo(0);
            }
        }
    }

    @Nested
    @DisplayName("Given a conjured item")
    class GivenConjuredItem {
        @Nested
        @DisplayName("When NOT PAST SELLIN DATE")
        class When {
            @Test
            @DisplayName("The quality decreases by 2")
            void itemDegrades() {
                storeWith(CONJURED_ITEM.getName(), 10, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(9);
                assertThat(quality()).isEqualTo(18);
            }
        }

        @Nested
        @DisplayName("When PAST SELLIN DATE")
        class WhenPastSellIn {
            @Test
            @DisplayName("The quality decreases by 4")
            void itemDegradesFast() {
                storeWith(CONJURED_ITEM.getName(), 0, 20);

                app.updateAtEndOfDay();

                assertThat(sellIn()).isEqualTo(-1);
                assertThat(quality()).isEqualTo(16);
            }
        }
    }

    private static void storeWith(String item, Integer sellIn, Integer quality) {
        app = GildedRose.createStoreWith(new Item(item, sellIn, quality));
    }

    private static Integer sellIn() {
        return app.getInventory().get(0).sellIn;
    }

    private static Integer quality() {
        return app.getInventory().get(0).quality;
    }
}
