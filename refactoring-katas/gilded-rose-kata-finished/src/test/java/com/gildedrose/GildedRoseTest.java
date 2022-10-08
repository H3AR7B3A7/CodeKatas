package com.gildedrose;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.gildedrose.storeItems.StoreItemFactory.*;
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
                GildedRose app = new GildedRose(items);

                app.updateQuality();

                assertEquals("foo", app.getItems()[0].name);
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

                app.updateQuality();

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

                app.updateQuality();

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

                app.updateQuality();

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
                storeWith(AGED_BRIE, 10, 20);

                app.updateQuality();

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
                storeWith(AGED_BRIE, 0, 20);

                app.updateQuality();

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
                storeWith(AGED_BRIE, 0, 49);

                app.updateQuality();

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
                storeWith(SULFURAS, 10, 20);

                app.updateQuality();

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
                storeWith(SULFURAS, 0, 0);

                app.updateQuality();

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
                storeWith(BACKSTAGE_PASSES, 11, 20);

                app.updateQuality();

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
                storeWith(BACKSTAGE_PASSES, 10, 20);

                app.updateQuality();

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
                storeWith(BACKSTAGE_PASSES, 5, 20);

                app.updateQuality();

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
                storeWith(BACKSTAGE_PASSES, 5, 49);

                app.updateQuality();

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
                storeWith(BACKSTAGE_PASSES, 0, 20);

                app.updateQuality();

                assertThat(sellIn()).isEqualTo(-1);
                assertThat(quality()).isEqualTo(0);
            }
        }
    }

    @Nested
    @DisplayName("Given a regular conjured item")
    class GivenConjuredItem {
        @Nested
        @DisplayName("When NOT PAST SELLIN DATE")
        class When {
            @Test
            @DisplayName("The quality decreases by 2")
            void itemDegrades() {
                storeWith("Conjured Mana Cake", 10, 20, true);

                app.updateQuality();

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
                storeWith("Conjured Mana Cake", 0, 20, true);

                app.updateQuality();

                assertThat(sellIn()).isEqualTo(-1);
                assertThat(quality()).isEqualTo(16);
            }
        }
    }

    /**
     * Since we made all items conjurable:
     * If conjured items perish 2ce as fast, and brie increases in quality as it perishes,
     * should conjured brie gain quality twice as fast?
     * What about Backstage Passes?
     */

    @Nested
    @DisplayName("Given conjured brie")
    @Disabled // TODO : Remove disabled when we decided on behavior
    class GivenConjuredBrie {
        @Nested
        @DisplayName("When NOT PAST SELLIN DATE")
        class When {
            @Test
            @DisplayName("The quality increases by 2")
            void itemDegrades() {
                storeWith(AGED_BRIE, 10, 20, true);

                app.updateQuality();

                assertThat(sellIn()).isEqualTo(9);
                assertThat(quality()).isEqualTo(22);
            }
        }

        @Nested
        @DisplayName("When PAST SELLIN DATE")
        class WhenPastSellIn {
            @Test
            @DisplayName("The quality increases by 4")
            void itemDegradesFast() {
                storeWith(AGED_BRIE, 0, 20, true);

                app.updateQuality();

                assertThat(sellIn()).isEqualTo(-1);
                assertThat(quality()).isEqualTo(24);
            }
        }
    }

    private static void storeWith(String name, Integer sellIn, Integer quality) {
        app = new GildedRose(new Item(name, sellIn, quality));
    }

    private static void storeWith(String name, Integer sellIn, Integer quality, boolean conjured) {
        app = new GildedRose(new Item(name, sellIn, quality));
        app.setAllCojured(conjured);
    }

    private static Integer sellIn() {
        return app.getItems()[0].sellIn;
    }

    private static Integer quality() {
        return app.getItems()[0].quality;
    }
}
