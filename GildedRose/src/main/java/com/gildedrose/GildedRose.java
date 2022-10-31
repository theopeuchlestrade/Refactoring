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
            if (item.sellIn != 0) item.sellIn--;

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
        if (item.sellIn == 0) {
            decreaseQuality(item);
        }
    }

    public void conjuredItemUpdates(Item item){
        itemQualityUpdates(item);
        itemQualityUpdates(item);
    }

    public void agedBrie(Item item){
        if (item.sellIn == 0) {
            incrementQuality(item);
            incrementQuality(item);
        } else {
            incrementQuality(item);
            item.sellIn--;
        }
    }

    public void backstagePassesToATAFKAL80ETCConcert(Item item){
        if (item.sellIn == 0) {
            item.quality = min_Quality;
        } else if (item.sellIn < 6) {
            incrementQuality(item);
            incrementQuality(item);
            incrementQuality(item);
            item.sellIn--;
        } else if (item.sellIn < 11) {
            incrementQuality(item);
            incrementQuality(item);
            item.sellIn--;
        } else {
            incrementQuality(item);
            item.sellIn--;
        }
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