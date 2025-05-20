import java.util.ArrayList;

public class IntersectionNetwork {
    public static ArrayList<IntersectionNetwork> intersectionNetworks = new ArrayList<>();
    //private ArrayList<ArrayList<Integer>> intersection = new ArrayList<>();

    public IntersectionNetwork() {
        intersectionNetworks.add(this);
        
        // int lanes = 3;
        // int numIntersections = 10;

        // for (int i = 0; i < lanes; i++) {
        //     ArrayList<Integer> lane = new ArrayList<>();
        //     for (int j = 0; j < numIntersections; j++) {
        //         // if it's the middle lane, populate with zeroes
        //         if (i % 2 == 1) {
        //             lane.add(0);
        //         } else { // if it's the outer lanes, populate with alternately with ones (intersections)
        //             // ternary operator (single line if/else)
        //             lane.add(j % 2 == 0 ? 1 : 0);
        //         }
        //     }
        //     intersection.add(lane);
        // }

    }

    public void moveThrough() {
        // do stuff
    }

    public void showIntersectionStatus() {
        
    }

}