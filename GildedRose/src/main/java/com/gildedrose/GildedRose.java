package com.gildedrose;

class GildedRose {
    Item[] items;

    int max_Quality = 50;

    int min_Quality = 0;

    int legendary_Quality = 80;

    String[] legendary_items = {
            ("Sulfuras, Hand of Ragnaros")
    };

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case "Aged Brie":
                    agedBrie(item);
                    break;

                case "Backstage passes to a TAFKAL80ETC concert":
                    backstagePassesToATAFKAL80ETCConcert(item);
                    break;

                default:
                    itemUpdates(item);
                    break;
            }
            validQuality(item);
        }
    }

    public void itemUpdates(Item item){
        if(notALegendaryItem(item)){
            item.sellIn--;
            if (item.name.contains("Conjured")) {
                conjuredItemUpdates(item);
            } else {
                itemQualityUpdates(item);
            }
        }
        else{
            item.quality = legendary_Quality;
        }
    }

    public void itemQualityUpdates(Item item){
        decreaseQuality(item);
        if (outOfDate(item)) {
            decreaseQuality(item);
        }
    }

    public void conjuredItemUpdates(Item item){
        itemQualityUpdates(item);
        itemQualityUpdates(item);
    }

    public void agedBrie(Item item){
        item.sellIn--;
        if (outOfDate(item)) {
            incrementQuality(item);
            incrementQuality(item);
        } else {
            incrementQuality(item);
        }
    }

    public void backstagePassesToATAFKAL80ETCConcert(Item item){
        item.sellIn--;
        if (outOfDate(item)) {
            item.quality = min_Quality;
        } else if (item.sellIn < 6) {
            incrementQuality(item);
            incrementQuality(item);
            incrementQuality(item);
        } else if (item.sellIn < 11) {
            incrementQuality(item);
            incrementQuality(item);
        } else {
            incrementQuality(item);
        }
    }

    public boolean outOfDate(Item item){
        return (item.sellIn < 0);
    }
    public void incrementQuality(Item item){
        item.quality += 1;
    }

    public void decreaseQuality(Item item){
        item.quality -= 1;
    }

    public void validQuality(Item item){
        if (item.quality > max_Quality && notALegendaryItem(item)){
            item.quality = max_Quality;
        }
        else if (item.quality < min_Quality){
            item.quality = min_Quality;
        }
    }

    public boolean notALegendaryItem(Item item){
        for (String l_item_name : legendary_items){
            if (l_item_name.equals(item.name)){
                item.quality = legendary_Quality;
                return false;
            }
        }
        return true;
    }

}