package mechanicStock.Classes;

public class Product{
    int productID;
    String productName;
    String productDescription;
    
    public Product(int ID, String Name, String Description) {
        this.productID = ID;
        this.productName = Name;
        this.productDescription = Description;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
