public abstract class Vehicle {
    protected String vehicleID;
    public static int count = 0;

    public Vehicle(int vehicleID) {
        // constructor
        this.vehicleID = String.valueOf(vehicleID);
        count++;
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