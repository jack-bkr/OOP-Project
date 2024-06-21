package mechanicStock.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class ProductTest {
    private Product product = new Product(1, "Shock Absorber", "Absorbs shocks");
    private ArrayList<Product> products = Product.getAllProducts();

    @Test
    void testGetProductID() {
        assertEquals(1, product.getProductID());
    }

    @Test
    void testGetProductName() {
        assertEquals("Shock Absorber", product.getProductName());
    }

    @Test
    void testGetProductDescription() {
        assertEquals("Absorbs shocks", product.getProductDescription());
    }

    @Test
    void testGetProductByID() {
        assertEquals(1, Product.getProductByID(1).getProductID());
    }

    @Test
    void testGetAllProducts() {
        ArrayList<Product> testProducts = Product.getAllProducts();
        assertEquals(products.size(), testProducts.size());
    }

    @Test
    void testAddProduct() {
        Product testProduct = Product.getProductByID(Product.addProduct("testProduct", "testDescription"));
        ArrayList<Product> testProducts = Product.getAllProducts();
        int testProductID = testProducts.get(testProducts.size() - 1).getProductID();
        assertEquals(testProductID, testProduct.getProductID());
    }

    @Test
    void testDeleteProduct() {
        int pID = Product.addProduct("testProduct", "testDescription");
        Product testProduct = Product.getProductByID(pID);
        assertTrue(testProduct.deleteItem());
    }

    @Test
    void testGetImage() {
        assertNotNull(product.getImage(100, 100));
    }

    @Test
    void testToString() {
        assertEquals("Shock Absorber", product.toString());
    }

}
