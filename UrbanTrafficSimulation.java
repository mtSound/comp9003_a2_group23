import java.util.Scanner;

import trafficSignal.TrafficSignal;

public class UrbanTrafficSimulation {
    public static Scanner scanner = new Scanner(System.in);
    public static String titleLine = new String(new char[80]).replace("\0", "=");
    public static String sectionLine = new String(new char[80]).replace("\0", "-");

    public static void main(String[] args) {

        // Authorship statement
        System.out.println("\nThis submission belongs to:\n\n" +
                "\t530049902, Michael Thomas\n" +
                "\t<SID>, Malavika Nandagopan\n" +
                "\t<SID>, Alex\n");

        // Program title
        System.out.println("\n" + titleLine);
        System.out.println("\n\t\t\t\033[1;4mUrban Traffic Simulation System\033[0m");
        System.out.println("\n" + titleLine + "\n");

        // instantiate objects
        int num;
        try {
            num = Integer.parseInt(args[0]);
        } catch (Exception e) {
            num = 3;
        }

        loadObjects(num);

        ///////////// MENU LOOP
        boolean programComplete = false;
        while (!programComplete) {

            // Show the menu
            showMenu();

            // prompt for user input
            String menuSelection = scanner.nextLine();
            System.out.println();

            // labelled switch statement to allow for exiting to main menu
            menuSelection: switch (menuSelection) {

                // MENU 1: Add Vehicle
                case "1":
                    System.out.println(
                            "\n---------------------------------- \033[1mAdd Vehicle\033[0m ---------------------------------\n");

                    boolean validVehicle = false;

                    // loop until valid input or 'cancel' are entered
                    while (!validVehicle) {
                        System.out.print("What kind of vehicle would you like to add? ('Car', 'Bus' or 'Truck'): ");
                        // int vehicleID = Vehicle.vehicles.size() + 1;

                        // prompt for user input (type of vehicle)
                        String vehicleType = scanner.nextLine().toLowerCase();

                        try {
                            validVehicle(vehicleType);

                            switch (vehicleType) {

                                // add a car
                                case "car":
                                    // new Car();
                                    validVehicle = true;
                                    break;

                                // add a bus
                                case "bus":
                                    // new Bus();
                                    validVehicle = true;
                                    break;

                                // add a truck
                                case "truck":
                                    // new Truck();
                                    validVehicle = true;
                                    break;

                                // return to main menu if user enters "cancel"
                                case "cancel":
                                    returnToMain();
                                    break menuSelection;

                                // should never happen
                                default:
                                    System.out.println(
                                            "\033[31mInvalid input. Please enter 'car', 'bus', 'truck', or 'cancel'\033[0m\n");
                            }
                            returnToMain();
                        } catch (SimulationException e) {
                            System.out.println("\n" + e +
                                    "\033[31mInvalid input. Please enter 'car', 'bus', 'truck', or 'cancel'\033[0m\n");
                        }

                    }

                    break;

                // MENU 2: Remove Vehicle
                case "2":
                    System.out.println(
                            "\n-------------------------------- \033[1mRemove Vehicle\033[0m --------------------------------\n");

                    // if (Vehicle.vehicles.size() == 0) {
                    //     System.out.println("No vehicles found!");
                    // } else {
                    //     System.out.println("Displaying " + Vehicle.vehicles.size() + " Vehicles...");
                    //     // display vehicles
                    //     for (Vehicle vehicle : Vehicle.vehicles) {
                    //         System.out.println(vehicle);
                    //     }

                    //     boolean validInput = false;
                    //     int indexToRemove;

                    //     while (!validInput) {
                    //         System.out.print("Enter the ID of the vehicle to remove or 'cancel': ");
                    //         String removeID = scanner.nextLine();

                    //         try {
                    //             if (validateInput(removeID) > 0) {
                    //                 for (int i = 0; i < Vehicle.vehicles.size(); i++) {
                    //                     if (removeID.equals(Vehicle.vehicles.get(i).getID())) {
                    //                         Vehicle.vehicles.remove(i);
                    //                         System.out.println("Vehicle " + i + " removed!\n");
                    //                         validInput = true;
                    //                         break;
                    //                     }
                    //                 }
                    //             } else {
                    //                 returnToMain();
                    //                 break menuSelection;
                    //             }
                    //         } catch (SimulationException e) {
                    //             System.out.println(e + "\033[31mInvalid Vehicle ID.\033[0m\n");
                    //         }

                    //         if (!validInput) {
                    //             System.out.println("\033[31mInvalid Vehicle ID.\033[0m\n");
                    //         }
                    //     }
                    // }
                    returnToMain();
                    break;

                // MENU 3: Adjust Signal Timers
                case "3":
                    System.out.println(
                            "\n----------------------------- \033[1mAdjust Signal Timers\033[0m -----------------------------\n");

                    System.out.println("Which signal would you like to adjust?\n");

                    // display submenu
                    for (TrafficSignal signal : TrafficSignal.trafficSignals) {
                        System.out.println(signal.getId() + ". Traffic Signal " + signal.getId());
                    }
                    System.out.println(TrafficSignal.trafficSignals.size() + 1 + ". All signals\n");

                    // input validation
                    boolean validInput = false;
                    String signalSelection;
                    int selection = 0;

                    while (!validInput) {
                        System.out.print("Make a selection: ");
                        signalSelection = scanner.nextLine();
                        System.out.println();

                        try {
                            if (validateInput(signalSelection) > 0) {
                                selection = Integer.parseInt(signalSelection);
                                validInput = true;
                            } else {
                                returnToMain();
                                break menuSelection;
                            }
                        } catch (SimulationException e) {
                            System.out.println(e +
                                    "\033[31mInvalid input. Please enter a numbered selection or 'cancel'\033[0m\n");

                        }

                    }

                    // set timer values
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

                        validInput = false;

                        // loop until valid input or 'cancel' are entered
                        while (!validInput) {
                            System.out.print("Set a new timer for the " + state + " signal: ");
                            String newTime = scanner.nextLine();

                            try {
                                if (validateInput(newTime) > 0) {
                                    newTimer[i] = Integer.parseInt(newTime);
                                    validInput = true;
                                } else {
                                    returnToMain();
                                    break menuSelection;
                                }
                            } catch (SimulationException e) {
                                System.out.println(
                                        "\n" + e + "\033[31mInput should be an integer greater than zero.\033[0m\n");
                            }
                        }
                    }

                    // adjust signals
                    if (selection == TrafficSignal.trafficSignals.size() + 1) {
                        for (TrafficSignal trafficSignal : TrafficSignal.trafficSignals) {
                            trafficSignal.setTimer(newTimer);
                        }
                        System.out.println("\nTimers adjusted for all Traffic Signals!\n");

                    } else {
                        for (TrafficSignal signal : TrafficSignal.trafficSignals) {
                            if (signal.getId().equals(String.valueOf(selection))) {
                                signal.setTimer(newTimer);
                            }
                        }
                        System.out.println("\nTimers adjusted for Traffic Signal " + selection + "\n");
                    }

                    returnToMain();
                    break;

                // MENU 4: View Traffic Status
                case "4":
                    System.out.println(
                            "\n----------------------------- \033[1mShow Traffic Status\033[0m ------------------------------\n");

                    if (!Vehicle.vehicles.isEmpty()) {
                        // Vehicle.showTrafficState();
                    } else {
                        System.out.println("No Vehicles to display.");
                    }

                    if (!TrafficSignal.trafficSignals.isEmpty()) {
                        TrafficSignal.showTrafficSignal();
                    } else {
                        System.out.println("No Traffic Signals to display.");
                    }

                    if (!IntersectionNetwork.intersectionNetworks.isEmpty()) {
                        // IntersectionNetwork.showIntersectionStatus();
                    } else {
                        System.out.println("No Intersection Networks to display.");
                    }

                    returnToMain();
                    break;

                // MENU 5: Run Simulation
                case "5":
                    System.out.println(
                            "\n-------------------------------- \033[1mRun Simulation\033[0m --------------------------------\n");

                    validInput = false;
                    int maxTime = 0;

                    // get user input & validate
                    while (!validInput) {
                        System.out.print("How many minutes would you like to run the simulation for: ");
                        String userTime = scanner.nextLine();

                        try {
                            if (validateInput(userTime) > 0) {
                                maxTime = Integer.parseInt(userTime);
                                validInput = true;
                            } else {
                                returnToMain();
                                break menuSelection;
                            }
                        } catch (SimulationException e) {
                            System.out.println(
                                    "\n" + e + "\033[31mMinutes should be an integer greater than zero.\033[0m\n");
                        }
                    }

                    // run a simulation
                    runSimulation(maxTime);
                    returnToMain();
                    break;

                // MENU 6: Exit
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
                    return;

                default:
                    // notify for invalid input
                    System.out.println("\033[31mInvalid input. Please enter a numbered selection, or 'exit'.\033[0m\n");
            }
        }

    }

    /**
     * Displays the Menu
     */
    public static void showMenu() {
        System.out.println("\033[1mSelect one of the following:\033[0m\n");
        System.out.println("\t\033[34m1. Add vehicle");
        System.out.println("\t2. Remove vehicle");
        System.out.println("\t3. Adjust traffic signal timers");
        System.out.println("\t4. View current traffic status");
        System.out.println("\t5. Run Simulation");
        System.out.println("\t6. Exit program\033[0m\n");
        System.out.print("Enter your numbered selection or 'exit': ");
    }

    /**
     * Displays relevant output before returning to main menu
     */
    public static void returnToMain() {
        System.out.println("\nReturning to main menu...");
        System.out.println("\n" + sectionLine);
        System.out.println();
    }

    /**
     * Instaniates Vehicles, Traffic Signals, and IntersectionNetworks
     * 
     * @param num - the number of each to load
     */
    public static void loadObjects(int num) {
        if (num == 3) {
            // new Car();
            // new Bus();
            // new Truck();
        } else {
            for (int i = 0; i < num; i++) {
                int random = (int) (Math.random() * 3) + 1;
                switch (random) {
                    case 1:
                        // new Car();
                        break;
                    case 2:
                        // new Bus();
                    case 3:
                        // new Truck();
                    default:
                        // new Car();
                }
            }
        }

        for (int i = 0; i < num; i++) {
            // new IntersectionNetwork();
            new TrafficSignal();
        }
    }

    /**
     * Validates an input String
     * 
     * @param input - the input String to validate
     * @return - 1 if valid input, -1 if invalid, and 0 if 'cancel'
     */
    public static int validateInput(String input) {
        // validates inputs
        if (input.toLowerCase().equals("cancel")) {
            return 0;
        } else {
            try {
                int num = Integer.parseInt(input);
                if (num < 1) {
                    throw new SimulationException("");
                } else {
                    return 1;
                }
            } catch (NumberFormatException e) {
                throw new SimulationException("");
            }
        }
    }

    /**
     * Validates a vehicle input string
     * 
     * @param input - input to validate
     * @return - boolean, whether or not the input is correct
     */
    public static boolean validVehicle(String input) {
        String[] validVehicles = new String[] { "car", "bus", "trucks" };

        boolean validVehicle = false;

        for (int i = 0; i < validVehicles.length; i++) {
            if (input.toLowerCase().equals(validVehicles[i])) {
                validVehicle = true;
            }
        }

        if (!validVehicle) {
            throw new SimulationException("");
        }

        return validVehicle;

    }

    /**
     * Runs the simulation
     * 
     * @param maxTime - the time in minutes to run the simulation for
     */
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
                            System.out.println(e + "Error! The signal cannot be changed");
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

class SimulationException extends RuntimeException {

    public SimulationException(String errorMessage) {
        super(errorMessage);
    }

    public SimulationException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

}