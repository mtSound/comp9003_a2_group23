public abstract class Vehicle {
    public static int count = 0;
    
    protected String vehicleID;

    private double velocity;
    private int lane;
    private int[] currentPosition;
    

    public Vehicle(int vehicleID) {
        // constructor
        this.vehicleID = String.valueOf(vehicleID);
        count++;
    }

}

class Car extends Vehicle {

    public Car(int vehicleID){
        // calculate velocity
        
        // constructor
        super(vehicleID);
    }

}

class Bus extends Vehicle {

    public Bus(int vehicleID){
        // calculate velocity
        
        // constructor
        super(vehicleID);
    }

}

class Truck extends Vehicle {

    public Truck(int vehicleID){
        // calculate velocity
        
        // constructor
        super(vehicleID);
    }

}