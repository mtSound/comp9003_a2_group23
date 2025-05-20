
// import java.util.ArrayList;
import java.util.Scanner;

import trafficSignal.TrafficSignal;
import simulationException.SimulationException;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static String sectionLine = new String(new char[80]).replace("\0", "-");

    public static void main(String[] args) {
        
        // Authorship statement
        System.out.println("\nThis submission belongs to:\n\n" +
                "\t530049902, Michael Thomas\n" +
                "\t<SID>, Malavika Nandagopan\n" +
                "\t<SID>, Alex\n");

        // section lines for breaking up user interface
        System.out.println("\n" + sectionLine);

        // Program title
        System.out.println("\n\t\t\t\033[1;4mUrban Traffic Simulation System\033[0m");

        // section lines for breaking up user interface
        System.out.println("\n" + sectionLine + "\n");


        // CREATE OBJECTS
        // new Car();
        // new Bus();
        // new Truck();
        // new IntersectionNetwork();
        // new IntersectionNetwork();
        // new IntersectionNetwork();
        new TrafficSignal();
        new TrafficSignal();
        new TrafficSignal();

        // int numObj = (args.length == 0) ? 3 : Integer.parseInt(args[0]);

        // if (args.length == 0) { 
        //     new Car();
        //     new Bus();
        //     new Truck();
        // }

        // for (int i = 0; i < numObj; i++) {
        //     new TrafficSignal();
        //     new IntersectionNetwork(); 
        // }

        boolean programComplete = false;

        while (!programComplete) {

            // Show the menu
            System.out.print(showMenu());

            // prompt for user input
            String menuSelection = scanner.nextLine();
            System.out.println();

            // labelled switch statement to allow for exiting to main menu
            menuSelection: switch (menuSelection) {

                case "1": // add vehicle
                    System.out.println(
                            "\n---------------------------------- \033[1mAdd Vehicle\033[0m ----------------------------------\n");

                    boolean validVehicle = false;

                    System.out.print("What kind of vehicle would you like to add? ('Car', 'Bus' or 'Truck': ");
                    // int vehicleID = Vehicle.vehicles.size() + 1;

                    // loop until valid input or 'cancel' are entered
                    while (!validVehicle) {
                        // prompt for user input (type of vehicle)
                        String vehicleType = scanner.nextLine().toLowerCase();

                        switch (vehicleType) {

                            // add a car
                            case "car":
                                // vehicles.add(new Car());
                                validVehicle = true;
                                break;

                            // add a bus
                            case "bus":
                                // vehicles.add(new Bus());
                                validVehicle = true;
                                break;

                            // add a truck
                            case "truck":
                                // vehicles.add(new Truck());
                                validVehicle = true;
                                break;

                            // return to main menu if user enters "cancel"
                            case "cancel":
                                System.out.print("Returning to main menu\n");

                                // section lines for breaking up user interface
                                System.out.println(sectionLine + "\n");

                                break menuSelection;

                            default:
                                System.out.println("\033[31mInvalid input. Please enter 'car', 'bus', or 'truck'\033[0m\n");
                        }

                        System.out.println("\nReturning to main menu...");
                        System.out.println("\n" + sectionLine);
                        System.out.println();
                    }

                    break;

                case "2": // remove vehicle
                    System.out.println(
                            "\n-------------------------------- \033[1mRemove Vehicle\033[0m ---------------------------------\n");

                    System.out.println("Displaying Vehicles...");

                    // // display vehicles
                    // for (Vehicle vehicle : Vehicle.vehicles) {
                    // System.out.println(vehicle);
                    // }

                    // boolean validID = false;
                    // int indexToRemove;

                    // while (!validID) {
                    // System.out.print("Enter the ID of the vehicle to remove or 'cancel': ");
                    // String removeID = scanner.nextLine();

                    // // return to main menu if user enters "cancel"
                    // if (removeID.toLowerCase().equals("cancel")) {
                    // System.out.print("Returning to main menu\n");
                    // // section lines for breaking up user interface
                    // System.out.println(sectionLine + "\n");
                    // break menuSelection;
                    // }

                    // // check that a valid ID was entered
                    // for (int i = 0; i < Vehicle.vehicles.size(); i++) {
                    // if (removeID.equals(Vehicle.vehicles.get(i).getID())) {
                    // validInput = true;
                    // indexToRemove = i;
                    // break;
                    // }
                    // }

                    // if (!validID) {
                    // System.out.println("\033[31mInvalid Vehicle ID.\033[0m\n");
                    // }
                    // }

                    // Vehicle.vehicles.remove(indexToRemove);

                    System.out.println("Vehicle removed!\n");
                    System.out.println("\nReturning to main menu...");
                    System.out.println("\n" + sectionLine);
                    System.out.println();

                    break;

                case "3": // adjust signal timers
                    System.out.println(
                            "\n----------------------------- \033[1mAdjust Signal Timers\033[0m ------------------------------\n");

                    String state;

                    int[] newTimer = new int[3];

                    for (int i = 0; i < newTimer.length; i++) {
                        if (i == 0) {
                            state = "Red";
                        } else if (i == 1) {
                            state = "Yellow";
                        } else {
                            state = "Green";
                        }

                        boolean validTime = false;

                        // loop until valid input or 'cancel' are entered
                        while (!validTime) {
                            System.out.print("Set a new timer for the " + state + " signal: ");
                            String newTimeStr = scanner.nextLine();

                            // return to main menu if user enters "cancel"
                            if (newTimeStr.toLowerCase().equals("cancel")) {
                                System.out.print("Returning to main menu\n");
                                // section lines for breaking up user interface
                                System.out.println(sectionLine + "\n");
                                break menuSelection;
                            }

                            // input validation for times
                            try {
                                int newTime = Integer.parseInt(newTimeStr);
                                newTimer[i] = newTime;
                                validTime = true;
                            } catch (NumberFormatException e) {
                                System.out.println("\n\033[31mInput should be an integer.\033[0m\n");
                            }
                        }
                    }

                    // set the traffic signals
                    for (TrafficSignal trafficSignal : TrafficSignal.trafficSignals) {
                        trafficSignal.setTimer(newTimer);
                    }

                    System.out.println("\nTimers adjusted\n");
                    System.out.println("\nReturning to main menu...");
                    System.out.println("\n" + sectionLine);
                    System.out.println();
                    break;

                case "4": // view traffic status
                    System.out.println(
                            "\n----------------------------- \033[1mShow Traffic Status\033[0m -------------------------------\n");

                    // Vehicle.showTrafficState();
                    TrafficSignal.showTrafficSignal();
                    System.out.println("\nReturning to main menu...");
                    System.out.println("\n" + sectionLine);
                    System.out.println();
                    break;

                case "5": // run simulation
                    System.out.println(
                            "\n-------------------------------- \033[1;32mRun Simulation\033[0m ---------------------------------\n");

                    boolean validTime = false;
                    int maxTime = 0;

                    // get user input & validate
                    while (!validTime) {
                        System.out.print("How many minutes would you like to run the simulation for?: ");
                        String userTime = scanner.nextLine();

                        // return to main menu if user enters "cancel"
                        if (userTime.toLowerCase().equals("cancel")) {
                            System.out.print("Returning to main menu\n");
                            // section lines for breaking up user interface
                            System.out.println(sectionLine + "\n");
                            break menuSelection;
                        }

                        // if user provides no input, set it to 5 as default
                        if (userTime.isEmpty()) {
                            maxTime = 5;
                             validTime = true;
                        } else {
                            // input validation for time
                            try {
                                maxTime = Integer.parseInt(userTime);
                                validTime = true;
                            } catch (NumberFormatException e) {
                                System.out.println("\n\033[31mMinutes should be an integer.\033[0m\n");
                            }
                        }
                    }

                    // run a simulation
                    runSimulation(maxTime);

                    System.out.println("\nReturning to main menu...");
                    System.out.println("\n" + sectionLine);
                    System.out.println();
                    break;

                case "6": // exit
                case "exit":
                    // close the scanner
                    if (scanner != null) {
                        scanner.close();
                    }

                    // display message
                    System.out.println("\nGoodbye!\n");

                    // exit the program
                    programComplete = true;

                // notify for invalid input
                default:
                    System.out.println("\033[31mInvalid input.\033[0m\n");
            }
        }

    }

    public static String showMenu() {
        // returns a formatted string to be displayed as the menu
        return "\033[1mSelect one of the following:\033[0m\n\n" +
                "\t\033[34m1. Add vehicle\n" +
                "\t2. Remove vehicle\n" +
                "\t3. Adjust traffic signal timers\n" +
                "\t4. View current traffic status\n" +
                "\t5. Run Simulation\n" +
                "\t6. Exit program\033[0m\n\n" +
                "Enter your numbered selection or 'exit': ";
    }

    public static void runSimulation(int maxTime) {

        // ensure that vehicles have been added
        if (Vehicle.vehicles.isEmpty()) {
            System.out.println("\n\033[31mError! You need to add at least 1 vehicle to the simulation.\033[0m\n");
        } else {
            System.out.println("\n\033[1;4mRunning simulation...\033[0m");

            // input provided in minutes, loop runs in seconds
            int maxTimeSeconds = maxTime * 60;

            // one step is considered 5 seconds
            for (int i = 0; i <= maxTimeSeconds; i += 5) {

                // // call the vehicle move method
                // for (Vehicle vehicle : Vehicle.vehicles) {

                // try {
                // vehicle.move();
                // } catch (SimulationException e) {
                // System.out.println("Error! Invalid lane change");
                // }

                // if (vehicle.moveCount == 5) {
                // vehicle.move();
                // vehicle.currentPosition();
                // }
                // }
                
                // update the signal
                for (TrafficSignal signal : TrafficSignal.trafficSignals) {
                    if (signal.checkCurrentState(i)) {
                        try {
                            signal.signal();
                        } catch (SimulationException e) {
                            System.out.println("Error! The signal cannot be changed");
                        }
                    }
                }
                
                // // call the intersectionNetwork moveThrough method
                // for (IntersectionNetwork intersectionNetwork :
                // IntersectionNetwork.intersectionNetworks) {
                // intersectionNetwork.moveThrough();
                // }

                // show the traffic states on the second call
                if (i / 5 == 2) {
                    // Vehicle.showTrafficState();
                    // IntersectionNetwork.showIntersectionStatus()
                    TrafficSignal.showTrafficSignal();
                }

                if (i % 60 == 0 && i > 0) {
                    System.out.println((i / 60) + " Minutes Completed!");
                }


                try {
                    Thread.sleep(500); // 500ms pause
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // show the traffic states after the final call
            // Vehicle.showTrafficState();
            TrafficSignal.showTrafficSignal();
            // IntersectionNetwork.showIntersectionStatus();

            System.out.println("Simulation complete!");
        }
    }

}