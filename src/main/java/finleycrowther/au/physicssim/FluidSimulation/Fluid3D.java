package finleycrowther.au.physicssim.FluidSimulation;

import finleycrowther.au.physicssim.Util.Utilities;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Sphere;

import java.util.List;

public class Fluid3D {
    final int WIDTH = 800;
    final int HEIGHT = 800;
    final double smoothingRadius = 75;
    double[][][][] D3Q19_Lattice;
    private final int[][] velocityDirections = {
            {0, 0, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0},
            {0, 0, 1}, {0, 0, -1}, {1, 1, 0}, {-1, -1, 0}, {1, -1, 0},
            {-1, 1, 0}, {1, 0, 1}, {-1, 0, -1}, {1, 0, -1}, {-1, 0, 1},
            {0, 1, 1}, {0, -1, -1}, {0, 1, -1}, {0, -1, 1}
    };

    static class FluidSimulation3D extends Pane {
        List<Utilities.Point3D> particles_locations;
        List<Sphere> particles;
        public FluidSimulation3D() {
            super();
        }

        private void generateParticles(int amount) {
            for(int i = 0; i < amount; i++) {

            }
        }
    }
}
