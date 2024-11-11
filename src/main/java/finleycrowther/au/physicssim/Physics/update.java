package finleycrowther.au.physicssim.Physics;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

public class update {
    private final List<Runnable> runnable;
    private final AnimationTimer t;

    boolean isTimerInit = false;
    boolean isRunning = false;

    long lastTick = System.nanoTime();

    public update(Runnable... methods) {
        // Stores the methods to be run on the tick
        this.runnable = new ArrayList<>(List.of(methods));

        this.t = new AnimationTimer() {
            @Override
            public void handle(long now) {

                // Print the time between ticks
                System.out.println((System.nanoTime() - lastTick)*0.000001 + "ms");
                lastTick = System.nanoTime();

                // Update the physics
                runnable.forEach(Runnable::run);
            }
        };

        isTimerInit = true;
    }

    public void add(Runnable r) {
        runnable.add(r);
    }

    public int getSize() {
        return runnable.size();
    }

    public void start() {
        // Check the timer is in the right state
        if(!isTimerInit)
            throw new IllegalStateException("Timer not initialized");

        t.start();
        isRunning = true;
    }

    public void stop() {
        // Check the timer is in the right state
        if(!isTimerInit)
            throw new IllegalStateException("Timer not initialized");
        else if (!isRunning)
            throw new IllegalStateException("Timer not running");

        t.stop();
    }

    public void replace(int updateIndex, Runnable update) {
        runnable.set(updateIndex, update);
    }
}
