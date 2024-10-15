package finleycrowther.au.physicssim.Physics;

import javafx.animation.AnimationTimer;

import java.util.List;

public class update {
    private List<Runnable> runnable;
    private AnimationTimer t;

    public update(Runnable... methods) {

        // Stores the methods to be run on the tick
        this.runnable = List.of(methods);

        this.t = new AnimationTimer() {
            @Override
            public void handle(long now) {

                // Update the physics
                runnable.forEach(Runnable::run);
            }


        };
    }

    public void add(Runnable r) {
        runnable.add(r);
    }

    public void start() {
        t.start();
    }

    public void stop() {
        t.stop();
    }
}
