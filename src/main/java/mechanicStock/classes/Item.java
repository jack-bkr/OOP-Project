package mechanicStock.classes;

import java.util.ArrayList;

public class Item extends Stock {
    // Attributes; getters
    private String vehicleName; public String getVehicleName() { return vehicleName; }
    private String productName; public String getProductName() { return productName; }
    private String productDescription; public String getProductDescription() { return productDescription; }
    
    // Constructor

    public Item(int ID, int pID, int vID, int Quantity, int BuyPrice, int SellPrice) {
        super(ID, pID, vID, Quantity, BuyPrice, SellPrice);
        this.vehicleName = super.vehicle.getVehicleMake() + " " + super.vehicle.getVehicleModel();
        this.productName = super.product.getProductName();
        this.productDescription = super.product.getProductDescription();
    }

    public String getInfo() {   //returns a string with all the information of the item, as displayed in the table
        return "ID: " + super.getStockID() + ", Vehicle: " + vehicleName + ", Product Name: " + super.product.getProductName()
                + ", Product Description: " + super.product.getProductDescription() + ", Quantity: "
                + super.getStockQuantity()
                + ", Buy Price: " + super.getBuyPrice() + ", Sell Price: " + super.getSellPrice();
    }
    
    public static ArrayList<Item> getAllItems() {   //returns an arraylist of all items in the stock
        ArrayList<Item> items = new ArrayList<Item>();

        for (Stock stock : Stock.getAllStock()) {
            Item item = new Item(stock.getStockID(), stock.getProductID(), stock.getVehicleID(),
                    stock.getStockQuantity(), stock.getBuyPrice(), stock.getSellPrice());
            items.add(item);
        }
        
        return items;
    }
}
