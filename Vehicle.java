import java.util.ArrayList;

public abstract class Vehicle {
    public static ArrayList<Vehicle> vehicles = new ArrayList<>();
    
    protected String vehicleID;

    public Vehicle(int vehicleID) {
        // constructor
        this.vehicleID = String.valueOf(vehicleID);
        vehicles.add(this);
    }

}

class Car extends Vehicle {

    public Car(int vehicleID){
         
        // constructor
        super(vehicleID);
    }

}

class Bus extends Vehicle {

    public Bus(int vehicleID){
        
        // constructor
        super(vehicleID);
    }

}

class Truck extends Vehicle {

    public Truck(int vehicleID){
        
        // constructor
        super(vehicleID);
    }

}