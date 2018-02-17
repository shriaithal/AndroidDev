package app.sample.sampleapplication;

/**
 * Counter Class to increment by 1
 * everytime onPause of any Activity is called
 * @author Anushri Srinath Aithal
 */

public class PauseCounter {

    private static PauseCounter pauseCounter = new PauseCounter();
    private Integer counter = 0;

    public static PauseCounter getInstance() {
        return pauseCounter;
    }

    public void addCount() {
        ++counter;
    }

    public Integer getCounter() {
        return counter;
    }

    public void resetCounter() {
        counter = 0;
    }
}
