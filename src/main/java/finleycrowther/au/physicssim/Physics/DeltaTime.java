package finleycrowther.au.physicssim.Physics;

import javafx.animation.AnimationTimer;

public class DeltaTime {
    private final AnimationTimer animationTimer;
    private long lastTime = 0; // Stores the time of the previous frame

    public DeltaTime() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastTime > 0) {
                    // Calculate delta time in seconds
                    double deltaTime = (now - lastTime) / 1_000_000_000.0;

                    // Update the logic using delta time
                    update(deltaTime);
                }
                // Update lastTime to the current frame's time
                lastTime = now;
            }
        };
    }

    public void start() {
        lastTime = System.nanoTime(); // Initialize lastTime
        animationTimer.start();
    }

    public void stop() {
        animationTimer.stop();
        lastTime = 0; // Reset lastTime
    }

    // Example update method that takes deltaTime
    private void update(double deltaTime) {
        System.out.println("Delta Time: " + deltaTime);

        // Use deltaTime to update object positions, physics, etc.
        // For example, moving an object with velocity:
        // objectPosition += objectVelocity * deltaTime;
    }
}
