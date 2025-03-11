package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ItemTest {

    //Con AssertEquals
    @Test
    public void ItemTestEquals() {
        Item item = new Item("Objeto", 5, 10);
        assertEquals("Objeto", item.name);
        assertEquals(5, item.sellIn);
        assertEquals(10, item.quality);
    }

    //Con AssertAll
    @Test
    public void ItemTest2() {
        Item item = new Item("Objeto", 5, 10);

        assertAll("UnaFrase",
        () -> assertEquals("Objeto", item.name),
        () -> assertEquals(5, item.sellIn),
        () -> assertEquals(10, item.quality));
    }

    //Otros Asserts
    @Test
    public void ItemTest3() {
        Item item = new Item("Objeto", 5, 10);

        assertNotEquals("Objeto2", item.name);
        assertNotNull(item.name);
        assertTrue(item.quality >= 0);
        assertFalse(item.quality < 0);
    }


}