package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void decrementabjetoGenerico(){
        Item[] items = new Item[] { new Item("objeto", 10,1)};

        Item itemResult = new Item ("objeto", 9, 0);
        var actual = GildedRose.updateQuality(items);
        assertEquals(itemResult, actual);

    }

}
