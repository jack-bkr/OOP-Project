package mechanicStock.classes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

class ItemTest{
    private Item item = new Item(1, 1, 1, 10, 50, 75);
    private ArrayList<Item> items = Item.getAllItems();

    @Test
    void testGetVehicleName() {
        assertEquals("Volkswagen Golf", item.getVehicleName());
    }

    @Test
    void testGetProductName() {
        assertEquals("Shock Absorber", item.getProductName());
    }

    @Test
    void testGetProductDescription() {
        assertEquals("Absorbs shocks", item.getProductDescription());
    }

    @Test
    void testGetAllItems() {
        ArrayList<Item> testItems = Item.getAllItems();
        assertEquals(items.size(), testItems.size());
    }
}