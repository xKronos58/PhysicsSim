package finleycrowther.au.physicssim.FluidSimulation;

import finleycrowther.au.physicssim.PhysicsSim;
import finleycrowther.au.physicssim.Util.Utilities;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static finleycrowther.au.physicssim.PhysicsSim.*;

public class Fluid extends Application {

    private static Scene ROOT;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ROOT = new Scene(new FluidSimulation(100), 800, 800);
        primaryStage.setScene(ROOT);
        primaryStage.show();
        primaryStage.setTitle("Fluid Simulation");
    }

    static class FluidSimulation extends Pane {

        List<Circle> allParticles;
        public FluidSimulation(int particles) {
            setPrefSize(WIDTH, HEIGHT);
            this.allParticles = buildParticles(particles);
            getChildren().addAll(allParticles);
        }

        private List<Circle> buildParticles(int particles) {
            List<Circle> temp = new ArrayList<>();
            for(int i = 0; i < particles; i++) {
                Utilities.Point3D point = generateRandomVector();
                Circle particle = new Circle(point.x, point.y, 5);
                temp.add(particle);
            }

            return temp;
        }

        /**
         * Allows for defined positions for the particles by including a List of vectors */
        private List<VectorCircle<Double, Double, Double>> buildVectors(int particles, List<Utilities.Point3D> positions) {
            return null;
        }

        private Utilities.Point3D generateRandomVector() {
            return new Utilities.Point3D(Math.random() * WIDTH, Math.random() * HEIGHT, 0d /*Currently 2d*/);
        }

    }

    static class VectorCircle<X, Y, Z> {
        X x;
        Y y;
        Z z;

        public VectorCircle(X x, Y y, Z z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
