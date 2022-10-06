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
                    break;

                case "Sulfuras, Hand of Ragnaros":
                    item.quality = 80; // Legendary
                    item.sellIn = 100_000_000;
                    break;

                default:
                    int quality_decreased = 1;

                    if (item.sellIn < 0 ) {
                        quality_decreased++;
                    }
                    if (item.name.contains("Conjured")){
                        quality_decreased *= 2;
                    }

                    item.quality -= quality_decreased;

                    if(item.quality < 0){
                        item.quality = 0;
                    }
                    break;
            }
            if (item.quality > 50 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.quality = 50;
            }
        }
    }
}