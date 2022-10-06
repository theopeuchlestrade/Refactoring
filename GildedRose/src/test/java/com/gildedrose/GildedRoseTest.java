package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GildedRoseTest {

  @Test
  @DisplayName("Test ttoString() function worked")
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
  @DisplayName("Test that the quality is unchanged")
  void testQuality() {
    Item element = new Item("foo", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].quality, is(0));
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
  @DisplayName("Test that the Aged Brie quality is changed")
  void testQualityAgedBrie() {
    Item element = new Item("Aged Brie", 10, 2);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].quality, is(3));
  }

  @Test
  @DisplayName("Test that the Aged Brie quality is changed")
  void testQualityAgedBrieSellIn0() {
    Item element = new Item("Aged Brie", 1, 49);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].quality, is(50));
  }

  @Test
  @DisplayName("Test Sulfuras is changed")
  void testItemSulfuras() {
    Item element = new Item("Sulfuras, Hand of Ragnaros", -1, 15);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(app.items[0].quality, is(15));
    assertThat(app.items[0].sellIn, is(-1));
  }



}
