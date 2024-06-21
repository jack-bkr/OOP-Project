package mechanicStock.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import mechanicStock.controllers.dbController;

import java.io.File;
import java.util.ArrayList;

public class StockTest {
    private static String[] fullpath = dbController.getPath().split(":");
    private static String path = fullpath[fullpath.length - 1];
    private Stock stock = new Stock(1, 1, 1, 13, 50, 75);
    private ArrayList<Stock> stocks = Stock.getAllStock();

    @Test
    void testGetStockID() {
        assertEquals(1, stock.getStockID());
    }

    @Test
    void testGetProductID() {
        assertEquals(1, stock.getProductID());
    }

    @Test
    void testGetVehicleID() {
        assertEquals(1, stock.getVehicleID());
    }

    @Test
    void testGetStockQuantity() {
        assertEquals(13, stock.getStockQuantity());
    }

    @Test
    void testGetBuyPrice() {
        assertEquals(50, stock.getBuyPrice());
    }

    @Test
    void testGetSellPrice() {
        assertEquals(75, stock.getSellPrice());
    }

    @Test
    void testGetStockByID() {
        assertEquals(1, Stock.getStockByID(1).getStockID());
    }

    @Test
    void testGetAllStock() {
        ArrayList<Stock> testStocks = Stock.getAllStock();
        assertEquals(stocks.size(), testStocks.size());
    }

    @Test
    void testAddStock() {
        assertTrue(Stock.addStock(1, 1, 0, 50, 75));
    }

    @Test
    void testDeleteItem() {
        Stock.addStock(0, 0, 0, 0, 0);
        Stock testStock = Stock.getAllStock().get(Stock.getAllStock().size() - 1);
        assertTrue(testStock.deleteItem());
    }

    @Test
    void testSetQuantity() {
        assertTrue(stock.setQuantity(10));
        assertEquals(10, stock.getStockQuantity());
    }

    @Test
    void testPrintInfo() {
        stock.printInfo();
        File file = new File(path + "\\Stock-" + stock.getStockID() + ".txt");
        assertTrue(file.isFile());
    }
}
