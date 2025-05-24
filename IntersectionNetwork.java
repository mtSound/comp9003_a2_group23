import simulationException.SimulationException;

public class IntersectionNetwork {

    static final int LANES = 3;
    static final int INTERSECTIONS = 10;

    private int[][] intersection;
    final private String vehicleID;
    private int currentLaneIndex = 0;
    private int currentIntersectionIndex;

    public IntersectionNetwork(String vehicleID) {

        this.vehicleID = vehicleID;
        this.currentLaneIndex = 0;
        this.currentIntersectionIndex = 0; //Current position at the start of the simulation is 0
        this.intersection = new int[LANES][INTERSECTIONS];

        for (int laneIndex = 0; laneIndex < LANES; laneIndex++) {
            for (int intersectionIndex = 0; intersectionIndex < INTERSECTIONS; intersectionIndex++) {
                intersection[laneIndex][intersectionIndex] = -1;
            }
        }
    }

    public void moveThrough(int lane) {
        try {
            //To check for invalid lane selection
            if(lane < 1 || lane > LANES ) {
                throw new SimulationException("Lane" + lane + " is Invalid.");
            }

            int laneIndex = lane - 1;  
            currentLaneIndex = laneIndex;

            if(currentIntersectionIndex >= INTERSECTIONS) {
                    System.out.println("Vehicle "+vehicleID+" has reached the end of the lane.");
            } else {
                if(lane == 1 || lane == LANES) { //To check if lanes are outer lanes.
                    System.out.println("Vehicle "+vehicleID+" moving through intersections");
                    intersection[laneIndex][currentIntersectionIndex] = (currentIntersectionIndex % 2 == 0) ? 1 : 0;
                } else { //Inner lanes do not have intersections.
                    intersection[laneIndex][currentIntersectionIndex] = 0;
                    System.out.println("Vehicle " + this.vehicleID + " moving. Lane " + lane + " does not have any intersections.");
                }

                currentIntersectionIndex++;
            }
           
        } catch (SimulationException e) {
            System.out.println("Simulation Error:" + e.getMessage());
        }
    }

    public void showIntersectionStatus() {
        System.out.println("\n=== Final Intersection Matrix (V = current position) ===");

        for(int laneIndex = 0; laneIndex < LANES; laneIndex++) {
            System.out.print("Lane " + (laneIndex + 1) + ": ");
            for(int intersectionIndex = 0; intersectionIndex < INTERSECTIONS; intersectionIndex++) {
                if(laneIndex == currentLaneIndex && intersectionIndex == currentIntersectionIndex) {
                    System.out.print("V ");
                }
                else {
                    int intersectionValue = intersection[laneIndex][intersectionIndex];
                    System.out.print((intersectionValue == -1 ? "-" : intersectionValue) + " ");
                }
            }
            System.out.println();
        }
    }
}
