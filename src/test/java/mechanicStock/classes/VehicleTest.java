package mechanicStock.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class VehicleTest {
    private Vehicle vehicle = new Vehicle(1, "Volkswagen", "Golf");
    private ArrayList<Vehicle> vehicles = Vehicle.getAllVehicles();

    @Test
    void testGetVehicleID() {
        assertEquals(1, vehicle.getVehicleID());
    }

    @Test
    void testGetVehicleMake() {
        assertEquals("Volkswagen", vehicle.getVehicleMake());
    }

    @Test
    void testGetVehicleModel() {
        assertEquals("Golf", vehicle.getVehicleModel());
    }

    @Test
    void testGetVehicleByID() {
        assertEquals(1, Vehicle.getVehicleByID(1).getVehicleID());
    }

    @Test
    void testGetAllVehicles() {
        ArrayList<Vehicle> testVehicles = Vehicle.getAllVehicles();
        assertEquals(vehicles.size(), testVehicles.size());
    }

    @Test
    void testAddVehicle() {
        Vehicle testVehicle = Vehicle.getVehicleByID(Vehicle.addVehicle("testMake", "testModel"));
        ArrayList<Vehicle> testVehicles = Vehicle.getAllVehicles();
        int testVehicleID = testVehicles.get(testVehicles.size() - 1).getVehicleID();
        assertEquals(testVehicleID, testVehicle.getVehicleID());
    }

    @Test
    void testDeleteVehicle() {
        int vID = Vehicle.addVehicle("testMake", "testModel");
        Vehicle testVehicle = Vehicle.getVehicleByID(vID);
        assertTrue(testVehicle.deleteItem());
    }

    @Test
    void testGetImage() {
        assertNotNull(vehicle.getImage(100, 100));
    }

    @Test
    void testToString() {
        assertEquals("Volkswagen Golf", vehicle.toString());
    }
}
