package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    Item item;

    @BeforeEach
    void setup() {
        item = new Item("Objeto", 5, 10);
    }

    //Con AssertEquals
    @Test
    public void ItemTestEquals() {
        assertEquals("Objeto", item.name);
        assertEquals(5, item.sellIn);
        assertEquals(10, item.quality);
    }

    //Con AssertAll
    @Test
    public void ItemTest2() {
        assertAll("UnaFrase",
        () -> assertEquals("Objeto", item.getName()), //o item.name sirve, así da mejor la cobertura
        () -> assertEquals(5, item.sellIn),
        () -> assertEquals(10, item.quality));
    }

    //Otros Asserts
    @Test
    public void ItemTest3() {
        assertNotEquals("Objeto2", item.name);
        assertNotNull(item.name);
        assertTrue(item.quality >= 0);
        assertFalse(item.quality < 0);
    }

    @Test
    public void ItemTestSetters() {
        item.setName("otro nombre");
        assertEquals("otro nombre", item.name);
        item.setQuality(12);
        assertEquals(12, item.quality);
        item.setSellIn(0);
        assertEquals(0, item.sellIn);
    }

    //Probando el método de Item
    @Test
    public void testItemToString(){
        assertEquals("Objeto, 5, 10", item.toString());
    }


}