package finleycrowther.au.physicssim.FluidSimulation;

import finleycrowther.au.physicssim.Physics.update;
import finleycrowther.au.physicssim.PhysicsSim;
import finleycrowther.au.physicssim.Util.Utilities;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Fluid2D extends Application {
    private static Scene ROOT;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static double smoothingRadius = 75;
    public static FluidSimulation2D simulation;
    /**Used for the Boltzmann lattice calculations */
    private static double[][][] D2Q9_Lattice;
    /**Used for the possible vector directions for the D2Q9 Lattice */
    int[][] velocityDirections2D = {
            {0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
    };

    // 3D testing not implemented
    private static double[][][][] D3Q19_Lattice;
    int[][] velocityDirections3D = {
            {0, 0, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0},
            {0, 0, 1}, {0, 0, -1}, {1, 1, 0}, {-1, -1, 0}, {1, -1, 0},
            {-1, 1, 0}, {1, 0, 1}, {-1, 0, -1}, {1, 0, -1}, {-1, 0, 1},
            {0, 1, 1}, {0, -1, -1}, {0, 1, -1}, {0, -1, 1}
    };

    /**
     * Class constructor */
    public Fluid2D() {
        simulation = new FluidSimulation2D(200);
    }

    /**
     * Overloaded class constructor
     * @param tick update to add the update method to
     */
    public Fluid2D(update tick) {
        simulation = new FluidSimulation2D(200, tick);
    }

    /**
     * Gets the smoothing radius for all particles
     * @return Double : smoothing radius */
    public static double getSmoothingRadius() {
        return smoothingRadius;
    }

    /**
     * Sets the smoothing radius for all particles */
    public static void setSmoothingRadius(double smoothingRadius) {
        Fluid2D.smoothingRadius = smoothingRadius;
        simulation.SetDensityCircleSize(smoothingRadius);

    }

    /**
     * Changes the particle counts to the provided integer
     * @param i amount of particles */
    public static void setParticleCount(int i) {
        if(i < 1) return;
        if(simulation.allParticles.size() < i) {
            List<Particle> newParticles = simulation.buildParticles(i - simulation.allParticles.size());
            List<Circle> newBounds = simulation.buildBoundsFromList(newParticles);
            newBounds.forEach(c -> c.setTranslateZ(-1));
            simulation.allParticles.addAll(newParticles);
            simulation.getChildren().addAll(newParticles.stream()
                    .map(Particle::display).toList());
            simulation.allBounds.addAll(newBounds);
            simulation.backLayer.getChildren().addAll(newBounds);
        }
        else if(simulation.allParticles.size() > i) {
            simulation.allParticles.subList(i, simulation.allParticles.size()).clear();
            simulation.allBounds.subList(i, simulation.allBounds.size()).clear();
            simulation.backLayer.getChildren().subList(i, simulation.backLayer.getChildren().size()).clear();
            simulation.getChildren().subList(i, simulation.getChildren().size()).clear();
        }
    }

    /**
     * Starts the main stage */
    @Override
    public void start(Stage primaryStage) throws Exception {
        simulation = new FluidSimulation2D(200);
        ROOT = new Scene(simulation, 800, 800, Color.BLACK);
        primaryStage.setScene(ROOT);
        primaryStage.show();
        primaryStage.setTitle("Fluid Simulation");
        new SimulationSettings().start(new Stage());

    }

    /**
     * Stores the simulation and calculates how the fluid should move */
    static class FluidSimulation2D extends Pane {
        /** List of all current particles in the simulation */
        List<Particle> allParticles;
        /** List of smoothing radius circles for each particle */
        List<Circle> allBounds;
        Pane backLayer;

        /**
         * Class constructor, builds the simulation
         * @param particles amount of start particles */
        public FluidSimulation2D(int particles) {
            backLayer = new Pane();
            backLayer.setStyle("-fx-background-color: black;");
            backLayer.setPrefSize(WIDTH, HEIGHT);
            setPrefSize(WIDTH, HEIGHT);
            this.allParticles = buildParticles(particles);
            allBounds = buildBounds();
            getChildren().add(backLayer);
            backLayer.getChildren().addAll(allBounds);
            getChildren().addAll(allParticles.stream()
                    .map(Particle::display).toList());
        }

        /**
         * Class constructor, builds the simulation
         * @param particles amount of start particles */
        public FluidSimulation2D(int particles, update tick) {
            backLayer = new Pane();
            backLayer.setStyle("-fx-background-color: black;");
            backLayer.setPrefSize(WIDTH, HEIGHT);
            setPrefSize(WIDTH, HEIGHT);
            this.allParticles = buildParticles(particles, tick);
            allBounds = buildBounds();
            getChildren().add(backLayer);
            backLayer.getChildren().addAll(allBounds);
            getChildren().addAll(allParticles.stream()
                    .map(Particle::display).toList());
        }

        /**
         * Builds the particles for the simulation
         * @param particles number of particles to build
         * @return List of particles */
        private List<Particle> buildParticles(int particles) {
            List<Particle> temp = new ArrayList<>();
            for(int i = 0; i < particles; i++) {
                Utilities.Point3D point = generateRandomVector();
                Circle particle = new Circle(point.x, point.y, 5, Color.WHITE);
                int finalI = i;
                temp.add(new Particle(particle, () -> defaultUpdate(finalI)));
            }

            return temp;
        }

        /**
         * Overload method for the buildParticles method
         * @param particles number of particles to build
         * @param tick update to add the update method to
         * @return List of particles */
        private List<Particle> buildParticles(int particles, update tick) {
            List<Particle> temp = new ArrayList<>();
            for(int i = 0; i < particles; i++) {
                Utilities.Point3D point = generateRandomVector();
                Circle particle = new Circle(point.x, point.y, 5, Color.WHITE);
                int finalI = i;
                temp.add(new Particle(particle, () -> defaultUpdate(finalI), tick));
            }

            return temp;
        }

        void defaultUpdate(int index) {
            double density = CalculateDensity(new Utilities.Vector<>(allParticles.get(index).getCenterX(), allParticles.get(index).getCenterY(), 0d));
            if(density > 0) {

            }
        }

        /** Gradient for visual representation of the smoothing radius */
        public RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
                new Stop(0, Color.BLUE),
                new Stop(0.7d, Color.TRANSPARENT),
                new Stop(1, Color.BLACK)
        });

        /**
         * Builds the bounds for the particles
         * @return List of bounds */
        private List<Circle> buildBounds() {
            List<Circle> temp = new ArrayList<>();
            this.allParticles.forEach(c -> {
                Circle bound = new Circle(c.getCenterX(), c.getCenterY(), getSmoothingRadius(), gradient);
                temp.add(bound);
            });
            return temp;
        }

        /**
         * Builds the bounds for the particles from a list of circles
         * @param circles List of circles to build bounds from
         * @return List of bounds */
        public List<Circle> buildBoundsFromList(List<Particle> circles) {
            List<Circle> temp = new ArrayList<>();
            circles.forEach(c -> {
                Circle bound = new Circle(c.getCenterX(), c.getCenterY(), getSmoothingRadius(), gradient);
                temp.add(bound);
            });
            return temp;
        }

        /**
         * Sets the density circle size for all particles
         * @param r radius of the circle */
        public void SetDensityCircleSize(double r) {
            allBounds.forEach(c -> c.setRadius(r));
        }

        /**
         * Allows for defined positions for the particles by including a List of vectors */
        private List<VectorCircle<Double, Double, Double>> buildVectors(int particles, List<Utilities.Point3D> positions) {
            return null;
        }

        /**
         * Generates a random vector for the particles
         * @return Point3D */
        private Utilities.Point3D generateRandomVector() {
            return new Utilities.Point3D(Math.random() * WIDTH, Math.random() * HEIGHT, 0d /*Currently 2d*/);
        }

        /**
         * Smoothing kernel for the particles
         * @param rad radius of the particle
         * @param dist distance from the particle
         * @return double */
        double SmoothingKernel(double rad, Double dist) {
            double volume = Math.PI * Math.pow(rad, 8) / 4;
            double value = Math.max(0, rad - dist);
            return value * value * value / volume;
        }

        /**
         * Calculates the density of the particles
         * @param SamplePoint point to sample
         * @return double */
        double CalculateDensity(Utilities.Vector<Double, Double, Double> SamplePoint) {
            final double[] density = {0};
            final double mass = 1;

            allParticles.forEach(p -> {
                double dist = Math.sqrt(Math.pow(SamplePoint.x - p.getCenterX(), 2) + Math.pow(SamplePoint.y - p.getCenterY(), 2));
                double influence = SmoothingKernel(getSmoothingRadius(), dist);
                density[0] += mass * influence;
            });

            return density[0];
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

    /**
     * Main class storing the logic for each particle */
    static class Particle {
        private final Circle display;
        private Runnable _update;
        int updateIndex = 0;

        /**
         * Class constructor
         * @param display Circle to display
         * @param _update Runnable to update */
        public Particle(Circle display, Runnable _update) {
            this.display = display;
            this._update = _update;
            // Add the update method to the tick
            updateIndex = PhysicsSim.tick.getSize();
            PhysicsSim.tick.add(this._update);
        }

        /**
         * Overloaded class constructor used for unit tests as the tick method is not initialized
         * due to this a test tick is parsed
         * @param display Circle to display
         * @param _update Runnable to update
         * @param tick update to add the update method to */
        public Particle(Circle display, Runnable _update, update tick) {
            this.display = display;
            this._update = _update;
            // Add the update method to the tick
            if(tick == null) throw new NullPointerException("Tick is null");

            updateIndex = tick.getSize();
            tick.add(this._update);
        }

        public double getCenterX() {
            return display.getCenterX();
        }

        public double getCenterY() {
            return display.getCenterY();
        }


        public Node display() {
            return this.display;
        }

        public Runnable getUpdate() {
            return _update;
        }

        public void setUpdate(Runnable update) {
            this._update = update;
            // Replace old method with new method in the tick
            PhysicsSim.tick.replace(updateIndex, update);
        }

        public void setUpdate(Runnable update, update tick) {
            this._update = update;
            // Replace old method with new method in the tick
            tick.replace(updateIndex, update);
        }

        /**
         * Adds velocity to a particle
         * @param velocity the velocity to be added
         * @param scalar scalar to multiply by */
        public void AddVelocity(Utilities.Vector<Double, Double, Double> velocity, double scalar) {
            setUpdate(() -> velocityUpdate(velocity, scalar));
        }

        /**
         * Overload for testing for the AddVelocity method
         * @param velocity velocity to add
         * @param scalar scalar to multiply by
         * @param tick update to add the update method to */
        public void AddVelocity(Utilities.Vector<Double, Double, Double> velocity, double scalar, update tick) {
            setUpdate(() -> velocityUpdate(velocity, scalar), tick);
        }

        void velocityUpdate(Utilities.Vector<Double, Double, Double> velocity, double scalar) {
            /*Will move until collision or until opposing velocity is added*/
            display.setCenterX(display.getCenterX() + velocity.x * scalar);
            display.setCenterY(display.getCenterY() + velocity.y * scalar);

            System.out.println("Velocity(CX,CY): " + getCenterX() + ", " + getCenterY());
            // Check if there are any particles in the smoothing radius of the particle
            double density = simulation.CalculateDensity(new Utilities.Vector<>(display.getCenterX(), display.getCenterY(), 0d));
            if(density > 0) {
                // If there are particles in the smoothing radius, add the opposite velocity
                display.setCenterX(display.getCenterX() - velocity.x * density);
                display.setCenterY(display.getCenterY() - velocity.y * density);
                System.out.println("Density: " + density);
                System.out.println("Center: " + getCenterX() + ", " + getCenterY());
            }
        }
    }
}
