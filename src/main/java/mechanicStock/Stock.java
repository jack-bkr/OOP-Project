package mechanicStock;

public class Stock {
    int stockID;
    int productID;
    int vehicleID;
    int stockQuantity;
    int buyPrice;
    int sellPrice;

    public Stock(int ID, int pID, int vID, int Quantity, int BuyPrice, int SellPrice) {
        this.stockID = ID;
        this.productID = pID;
        this.vehicleID = vID;
        this.stockQuantity = Quantity;
        this.buyPrice = BuyPrice;
        this.sellPrice = SellPrice;
    }

    
}
