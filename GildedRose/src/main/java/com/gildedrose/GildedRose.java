package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.sellIn --;
            switch(item.name) {
                case "Aged Brie":
                    if (item.sellIn < 0) {
                        item.quality += 2;
                    } else {
                        item.quality += 1;
                    }

                    if (item.quality > 50) {
                        item.quality = 50;
                    }
                    break;

                case "Backstage passes to a TAFKAL80ETC concert":
                    if (item.sellIn < 0) {
                        item.quality = 0;
                    } else if (item.sellIn < 6) {
                        item.quality += 3;
                    } else if (item.sellIn < 11) {
                        item.quality += 2;
                    } else {
                        item.quality += 1;
                    }
                    if (item.quality > 50) {
                        item.quality = 50;
                    }
                    break;

                case "Sulfuras, Hand of Ragnaros":
                    item.quality = 80;
                    item.sellIn = 100_000_000;
                    break;

                default:
                    int quality_decreased = 0;
                    if (item.quality > 0) {
                        if (item.sellIn < 0 && item.quality > 1) {
                            quality_decreased = 2;
                        } else {
                            quality_decreased = 1;
                        }
                    }

                    if (item.name.contains("Conjured")){
                        quality_decreased *= 2;
                    }

                    item.quality -= quality_decreased;

                    if(item.quality <0){
                        item.quality = 0;
                    }
                    break;
            }
        }
    }
}