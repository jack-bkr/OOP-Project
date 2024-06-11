package mechanicStock.classes;

import java.util.ArrayList;

public class Item extends Stock {
    private int stockID; public int getStockID() { return stockID; }
    private String vehicleName; public String getVehicleName() { return vehicleName; }
    private String productName; public String getProductName() { return productName; }
    private String productDescription; public String getProductDescription() { return productDescription; }
    private int stockQuantity; public int getStockQuantity() { return stockQuantity; }
    private int buyPrice; public int getBuyPrice() { return buyPrice; }
    private int sellPrice; public int getSellPrice() { return sellPrice; }

    public Item(int ID, int pID, int vID, int Quantity, int BuyPrice, int SellPrice) {
        super(ID, pID, vID, Quantity, BuyPrice, SellPrice);
        this.vehicleName = super.vehicle.getVehicleMake() + " " + super.vehicle.getVehicleModel();
        this.productName = super.product.getProductName();
        this.productDescription = super.product.getProductDescription();
        this.stockQuantity = super.getStockQuantity();
        this.buyPrice = super.getBuyPrice();
        this.sellPrice = super.getSellPrice();
    }

    public String getInfo() {
        return "ID: " + stockID + ", Vehicle: " + vehicleName + ", Product Name: " + super.product.getProductName()
                + ", Product Description: " + super.product.getProductDescription() + ", Quantity: "
                + super.getStockQuantity()
                + ", Buy Price: " + super.getBuyPrice() + ", Sell Price: " + super.getSellPrice();
    }
    
    public static ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<Item>();

        for (Stock stock : Stock.getAllStock()) {
            Item item = new Item(stock.getStockID(), stock.getProductID(), stock.getVehicleID(),
                    stock.getStockQuantity(), stock.getBuyPrice(), stock.getSellPrice());
            items.add(item);
        }
        
        return items;
    }
}
