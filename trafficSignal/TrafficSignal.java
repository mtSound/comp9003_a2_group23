/*
 * Package: TrafficSignal
 * Author: Michael Thomas
 * SID: 530049902
 * Description: This class defines a 'Traffic Signal' for use in an Urban Traffic Simulation.
 * The class defines:
 * 
 *          trafficSignals:         An ArrayList of all instances of a trafficSignal 
 * 
 * Each TrafficSignal has the following attributes:
 *          
 *          signalId (String):      A unique identifier for the TrafficSignal
 *          timer (int array):      An array of integers representing the duration for each signal
 *          timeChanged (int):      An int holding the last time a signal was changed
 *          stateIndex (int):       An int corresponding to the signal's current state
 *          currentState (String):  The current state of the signal (red, yellow or green) 
 * 
 * And the following methods:
 * 
 *          getID()                 Returns the signalId
 *          getCurrentState()       Returns the currentState
 *          checkCurrentState()     Returns a boolean value based on whether the signal should change
 *          setTimer()              Sets the timer array
 *          signal()                Changes the current signal to the next value in the array
 *          showTrafficSignal()     Displays the current state of all traffic signals
 * 
 */

package trafficSignal;

import java.util.ArrayList;
import simulationException.SimulationException;

public class TrafficSignal {

    // ArrayList of all Traffic Signals
    public static ArrayList<TrafficSignal> trafficSignals = new ArrayList<>();

    // allowed states
    protected static final String[] states = new String[] { "red", "yellow", "green" };

    // instance attributes
    private String signalId;
    private int[] timer = new int[3];
    private int timeChanged = 0;
    private int stateIndex = 0;
    private String currentState;

    // default constructor
    public TrafficSignal() {
        // set timers
        this.timer[0] = 10;
        this.timer[1] = 2;
        this.timer[2] = 15;

        // add to arraylist
        trafficSignals.add(this);

        // set the signalId
        this.signalId = Integer.toString(trafficSignals.size());
    }

    // overloaded constructor, providing signalId
    public TrafficSignal(String signalId) {
        // set timers
        this.timer[0] = 10;
        this.timer[1] = 2;
        this.timer[2] = 15;

        // set the signalId
        this.signalId = signalId;

        // add to arraylist
        trafficSignals.add(this);

    }

    // overloaded constructor, providing signalID and timer[]
    public TrafficSignal(String signalId, int[] newTimer) {
        // set timers
        for (int i = 0; i < timer.length; i++) {
            this.timer[i] = newTimer[i];
        }

        // set the signalId
        this.signalId = signalId;

        // add to arraylist
        trafficSignals.add(this);

    }

    // overloaded constructor, providing timer[]
    public TrafficSignal(int[] newTimer) {
        // set timers
        for (int i = 0; i < timer.length; i++) {
            this.timer[i] = newTimer[i];
        }

        // add to arraylist
        trafficSignals.add(this);

        // set the signalId
        this.signalId = Integer.toString(trafficSignals.size());

    }

    // Main method for testing
    public static void main(String[] args) {
        String sectionLine = new String(new char[111]).replace("\0", "-");
        int maxTime = 5;

        new TrafficSignal();
        new TrafficSignal();
        new TrafficSignal();

        System.out.println(sectionLine + "\n");
        System.out.println("\033[1;4;33mRunning simulation...\033[0m\n");

        // input provided in minutes, loop runs in seconds
        int maxTimeSeconds = maxTime * 60;

        // one step is considered 5 seconds
        for (int i = 0; i <= maxTimeSeconds; i += 5) {

            // // call the vehicle move method
            // for (Vehicle vehicle : Vehicle.vehicles) {
            // if (vehicle.moveCount < 5) {
            // vehicle.move();
            // }
            // }

            // // call the intersectionNetwork moveThrough method
            // for (IntersectionNetwork intersectionNetwork :
            // IntersectionNetwork.intersectionNetworks) {
            // intersectionNetwork.moveThrough();
            // }

            // update the signal
            for (TrafficSignal signal : TrafficSignal.trafficSignals) {
                if (signal.checkCurrentState(i)) {
                    signal.signal();
                }
            }

            // show the vehicle traffic state on the second call
            if (i / 5 == 2) {
                // Vehicle.showTrafficState();
            }

            if (i % 60 == 0 && i > 0) {
                System.out.println((i / 60) + " Minutes Completed!");
            }
        }

        // Vehicle.showTrafficState();
        TrafficSignal.showTrafficSignal();
        // IntersectionNetwork.showIntersectionStatus();

        System.out.println("\n\033[32mSimulation complete!\n\033[0m");
        System.out.println(sectionLine + "\n\n");
    }

    public static void showTrafficSignal() {
        // show current traffic status of all vehicles
        System.out.println("\n\033[1;4mSignal states:\033[0m\n");
        for (TrafficSignal signal : trafficSignals) {
            System.out.println(signal);
        }
    }

    public String getId() {
        // return the signalId
        return this.signalId;
    }

    public String getCurrentState() {
        // return the current state
        return currentState;
    }

    public boolean checkCurrentState(int timeElapsed) {
        // checks if the signal needs to be changed
        boolean signalChange = false;

        // cumulative signal times
        int[] signalTimes = new int[] { (timer[0]), (timer[0] + timer[1]), (timer[0] + timer[1] + timer[2]) };

        // evaluate whether the signal needs to be changed based on timeElapsed
        for (int i = 0; i < signalTimes.length; i++) {
            if (signalTimes[i] > timeChanged) {
                if (signalTimes[i] <= timeElapsed) {
                    signalChange = true;
                    timeChanged = timeElapsed;
                    break;
                }
            }
        }

        return signalChange;
    }

    public void setTimer(int newTimer[]) {
        // change the timers
        for (int i = 0; i < timer.length; i++) {
            this.timer[i] = newTimer[i];
        }
    }

    public void signal() {
        
        // cycle through states, throw a SimulationException if the signal can't be changed
        try {
            currentState = states[stateIndex];
            System.out.println("Traffic signal #" + getId() + " changed to " + getCurrentState());
            stateIndex++;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimulationException("Simulation Exception: ", e);
        }
        
    }

    public String toString() {
        return "Traffic Signal #" + getId() + ": " + getCurrentState();
    }

}