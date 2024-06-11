package mechanicStock.Classes;

public class Vehicle{
    int vehicleID;
    String vehicleMake;
    String vehicleModel;

    public Vehicle(int ID, String Make, String Model) {
        this.vehicleID = ID;
        this.vehicleMake = Make;
        this.vehicleModel = Model;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }
}