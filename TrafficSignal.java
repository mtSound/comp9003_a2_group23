public class TrafficSignal {
    
    protected static final String[] states = new String[]{"red", "yellow", "green"};
    
    private String signalId;
    private String currentState;
    private int[] timer = new int[]{10, 2, 15};

    public TrafficSignal() {
        // constructor
    }


    public String getId() {
        return this.signalId;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setTimer(int newTime[]) {
        // change the timers
    }

    public void signal(int time) {
        // cycle through states
    }

    public void showTrafficSignal() {
        // show traffic signal
    }
}
