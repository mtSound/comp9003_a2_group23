import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final String sectionLine = new String(new char[111]).replace("\0", "-");

    public static ArrayList<Vehicle> vehicles = new ArrayList<>();
    public static ArrayList<TrafficSignal> trafficSignals = new ArrayList<>();
    public static ArrayList<IntersectionNetwork> intersectionNetworks = new ArrayList<>();


    public static void main(String[] args) {
        // Authorship statement
        System.out.println("\nThis submission belongs to:\n\n" + 
            "\t530049902, Michael Thomas\n" + 
            "\t<SID>, <name>\n" + 
            "\t<SID>, <name>\n");

        // section lines for breaking up user interface
        System.out.println("\n" + sectionLine);
        
        // Program title
        System.out.println("\n\t\t\t\033[1;4mUrban Traffic Simulation System\033[0m");

        // section lines for breaking up user interface
        System.out.println("\n" + sectionLine + "\n");


        while (true) {

            // Show the menu
            System.out.print(showMenu());
            
            // prompt for user input
            String menuSelection = scanner.nextLine();
            System.out.println();
            
            // labelled switch statement to allow for exiting to main menu
            menuSelection: switch(menuSelection) {

                case "1": //add vehicle
                    boolean validInput = false;
                    
                    System.out.println("What kind of vehicle would you like to add? ('Car', 'Bus' or 'Truck')");
                    int vehicleID = Vehicle.count + 1;

                    while (!validInput){
                        // prompt for user input
                        String vehicleType = scanner.nextLine();
                        
                        switch(vehicleType) {
                            case "Car":
                                
                                vehicles.add(new Car(vehicleID));
                                
                                validInput = true;
                                break;
                                
                            case "Bus":
                                
                                vehicles.add(new Bus(vehicleID));
                                
                                validInput = true;
                                break;
                                
                            case "Truck":
                                
                                vehicles.add(new Truck(vehicleID));
                            
                                validInput = true;
                                break;

                            case "Cancel":
                            case "cancel":
                                break menuSelection;

                            default:
                                System.out.println("\033[31mInvalid input.\033[0m\n");
                        }
                    }

                    break;
                
                case "2": // remove vehicle
                    //
                    System.out.println("Vehicle removed!\n");
                    break;
                
                case "3": // adjust signal timers
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
                        while (!validTime) {
                            System.out.print("Set a new timer for the " + state + " signal: ");
                            String newTimeStr = scanner.nextLine();

                            try {
                                int newTime = Integer.parseInt(newTimeStr);
                                newTimer[i] = newTime;
                                validTime = true;
                            } catch (NumberFormatException e) {
                                System.out.println("\n\033[31mInput should be an integer.\033[0m\n");
                            }
                        }
                    }

                    System.out.println("Timers adjusted!\n");
                    break;
                
                case "4": // view traffic status
                    //
                    break;

                case "5": // run simulation
                    boolean validTime = false;
                    int maxTime = 0;

                    // get user input & validate
                    while (!validTime) {
                        System.out.print("How many minutes would you like to run the simulation for?: ");
                        String userTime = scanner.nextLine();
    
                        try {
                            maxTime = Integer.parseInt(userTime);
                            validTime = true;
                        } catch (NumberFormatException e) {
                            System.out.println("\n\033[31mMinutes should be an integer.\033[0m\n");
                        }
                    }

                    runSimulation(maxTime);

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
                    return;

                // notify for invalid input
                default:
                    System.out.println("\033[31mInvalid input.\033[0m\n");
            }
        }

    }

    public static String showMenu() {
        // returns a string to be displayed as the menu

        return "\033[1mSelect one of the following:033[0m\n\n" +
        "\t\033[34m1. Add vehicle\n" + 
        "\t2. Remove vehicle\n" + 
        "\t3. Adjust traffic signal timers\n" + 
        "\t4. View current traffic status\n" +
        "\t5. Run Simulation\n" + 
        "\t6. Exit program\033[0m\n\n" +
        "Enter your numbered selection or 'exit': ";
    }

    public static void runSimulation(int maxTime) {
        // input provided in minutes, loop run in seconds

        int maxTimeSeconds = maxTime * 60;

        // one step is considered 5 seconds
        for (int i = 0; i <= maxTimeSeconds; i += 5) {
            // do stuff

            if (i % 60 == 0 && i > 0) {
                System.out.println((i / 60) + " Minutes Completed!");
            }

        }

    }

}