package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GildedRoseTest {
    
    //Tests del Queso
    @Test
    public void testAgedBrieNormal() {
        Item item = new Item("Aged Brie", 20, 0);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(19, item.getSellIn());
        assertEquals(1, item.getQuality());
    }

    @Test
    public void testAgedBrieDouble() {
        Item item = new Item("Aged Brie", 0, 2);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(-1, item.getSellIn());
        assertEquals(4, item.getQuality());
    }

    //TestObjetoComun
    @Test
    public void testCommonItem() {
        Item item = new Item("Common Item", 20, 5);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(19, item.getSellIn());
        assertEquals(4, item.getQuality());
    }

    @Test
    public void testCommonObjectDoubleDecrease(){
        Item item = new Item("Common Item", 0, 5);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(-1, item.getSellIn());
        assertEquals(3, item.getQuality());
    }

    //Sulfuras
    @Test
    public void testSulfuras(){
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(80, item.getQuality());
        assertEquals(0, item.getSellIn());
    }

    //Backstage passes
    @Test
    public void testBackstagePassesNormal(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 20);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(19, item.getSellIn());
        assertEquals(21, item.getQuality());
    }

    @Test
    public void testBackstagePassesDouble(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(12, item.getQuality());
    }

    @Test
    public void testBackstagePassesTriple(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(43, item.getQuality());
    }

    @Test
    public void testBackstageSellIn0LossesQ(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 40);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testMaxQualityReached(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        GildedRose app = new GildedRose(new Item[] { item });
        app.updateQuality();
        assertEquals(50, item.getQuality());
    }

    //Otros
    



}
