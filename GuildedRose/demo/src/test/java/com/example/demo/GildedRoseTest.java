package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GildedRoseTest {
    

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



}
