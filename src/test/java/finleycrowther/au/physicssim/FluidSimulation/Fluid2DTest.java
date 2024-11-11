package finleycrowther.au.physicssim.FluidSimulation;

import finleycrowther.au.physicssim.Physics.update;
import finleycrowther.au.physicssim.Util.Utilities;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Fluid2DTest {

    @BeforeAll
    static void initToolkit() {
        // This ensures JavaFX toolkit is initialized
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void AddVelocity() throws InterruptedException {
        update tick = new update(() -> {});
        tick.start();
        Fluid2D fluid = new Fluid2D(tick);

        // fluid.simulation is not static as the main class is not static however for internal use it has been defined as static so the warning can be ignored
        fluid.simulation.allParticles.getFirst().AddVelocity(new Utilities.Vector<>(1d, 1d, 1d), 2, tick);

        // Code to simulate a running application / simulation to allow the tick to run to test the velocity
        Thread wait = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        wait.start();


        try {
            wait.join();  // This will block until `wait` finishes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Done");
    }
}