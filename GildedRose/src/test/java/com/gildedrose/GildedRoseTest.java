package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GildedRoseTest {

  @Test
  @DisplayName("Test toString() function worked")
  void testToString() {
    Item element = new Item("foo", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    assertThat(app.items[0].toString(), is("foo, 0, 0"));
  }

  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    Item element = new Item("foo", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].name, is("foo"));
  }

  @Test
  @DisplayName("Test that the quality is changed with sellIn negative value")
  void testQuality() {
    Item element = new Item("foo", -1, 10);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].sellIn, is(-2));
    assertThat(app.items[0].quality, is(8));
  }

  @Test
  @DisplayName("Test that the quality maxed out")
  void testQualityMaxed() {
    Item element = new Item("Aged Brie", 10, 49);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].quality, is(50));
  }

  @Test
  @DisplayName("Test that the quality is changed")
  void testQualityGildedRose() {
    Item element = new Item("foo", 10, 1);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].quality, is(0));
  }

  @Test
  @DisplayName("Test that the sellIn is changed")
  void testSellIn() {
    Item element = new Item("foo", 2, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(element.sellIn, is(1));
  }

  @Test
  @DisplayName("Test that the quality is updated for each item of app")
  void testMultipleQualityUpdate() {
    Item element1 = new Item("poo", 2, 10);
    Item element2 = new Item("foo", 2, 10);
    GildedRose app = new GildedRose(new Item[] {element1, element2});
    app.updateQuality();
    assertThat(app.items[0].quality, is(9));
    assertThat(app.items[1].quality, is(9));
  }

  @Test
  @DisplayName("Test that the Aged Brie quality is unchanged")
  void testMaxedQualityAgedBrie() {
    Item element = new Item("Aged Brie", 1, 50);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].quality, is(50));
  }

  @Test
  @DisplayName("Test that the Aged Brie quality is changed")
  void testQualityAgedBrieSellIn0() {
    Item element = new Item("Aged Brie", 1, 49);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].sellIn, is(0));
    assertThat(app.items[0].quality, is(50));
  }

  @Test
  @DisplayName("Test that the Aged Brie quality is changed and sellIn < 0")
  void testQualityAgedBrieInf0() {
    Item element1 = new Item("Aged Brie", 0, 48);
    Item element2 = new Item("Aged Brie", 0, 49);
    GildedRose app = new GildedRose(new Item[] {element1, element2});
    app.updateQuality();
    assertThat(app.items[0].sellIn, is(-1));
    assertThat(app.items[0].quality, is(50));
    assertThat(app.items[1].sellIn, is(-1));
    assertThat(app.items[1].quality, is(50));
  }

  @Test
  @DisplayName("Test Sulfuras is changed")
  void testItemSulfuras() {
    Item element = new Item("Sulfuras, Hand of Ragnaros", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].quality, is(80));
    assertThat(app.items[0].sellIn, is(100_000_000));
  }

  @Test
  @DisplayName("Test 'Backstage passes to a TAFKAL80ETC concert' item")
  void testBackstageItem() {
    Item element1 = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0);
    Item element2 = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0);
    Item element1b = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
    Item element2b = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
    Item element3 = new Item("Backstage passes to a TAFKAL80ETC concert", 13, 0);
    Item element4 = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50);

    GildedRose app = new GildedRose(new Item[] {element1, element2, element3, element4, element1b, element2b});

    app.updateQuality();
    // Test for quality < 6
    assertThat(app.items[0].sellIn, is(4));
    assertThat(app.items[0].quality, is(3));
    // Test for quality < 11
    assertThat(app.items[1].sellIn, is(9));
    assertThat(app.items[1].quality, is(2));
    // Test for quality > 11
    assertThat(app.items[2].sellIn, is(12));
    assertThat(app.items[2].quality, is(1));
    // Test for sellIn < 0
    assertThat(app.items[3].sellIn, is(-1));
    assertThat(app.items[3].quality, is(0));

    // Test if quality > 50
    // Test for quality < 6
    assertThat(app.items[4].sellIn, is(4));
    assertThat(app.items[4].quality, is(50));
    // Test for quality < 11
    assertThat(app.items[5].sellIn, is(9));
    assertThat(app.items[5].quality, is(50));
  }

  @Test
  @DisplayName("Test Conjured item")
  void testConjuredItem(){
    Item element1 = new Item("Conjured Shoes", 10, 20);
    Item element2 = new Item("Conjured Shoes", 0, 20);
    GildedRose app = new GildedRose(new Item[] {element1, element2});
    app.updateQuality();
    assertThat(element1.quality, is(18));
    assertThat(element1.sellIn, is(9));

    assertThat(element2.quality, is(16));
    assertThat(element2.sellIn, is(-1));
  }

}
