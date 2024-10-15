package finleycrowther.au.physicssim.Shapes;

import finleycrowther.au.physicssim.Util.Utilities.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Shape {
    Double surfaces;
    boolean is3D;
    boolean isSmooth;
    boolean isPolygon;
    boolean isHollow;
    Double Mass;
    Double Volume;
    Double Density;
    Material material;
    Vector<Double, Double, Double> Position;
    Vector<Double, Double, Double> Velocity;
    Vector<Double, Double, Double> Acceleration;
    Axis<X, Y, Z, X> axis;

    public Shape(Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
        this.surfaces = surfaces;
        this.is3D = is3D;
        this.isSmooth = isSmooth;
        this.isPolygon = isPolygon;
        this.isHollow = isHollow;
        this.Mass = Mass;
        this.Volume = Volume;
        this.Density = Density;
        this.material = material;
        this.Position = Position;
        this.Velocity = Velocity;
        this.Acceleration = Acceleration;

    }

    public Axis<X, Y, Z, X> getAxis() {
        return axis;
    }

    public Axis<X, Y, Z, X> setAxis() {
        return axis;
    }

    public double SurfaceArea() {
        throw new UnsupportedOperationException("A shape type must be specified.");
    }

    public double getSurfaces() { return surfaces; }
    public boolean is3D() { return is3D; }
    public boolean isSmooth() { return isSmooth; }
    public boolean isPolygon() { return isPolygon; }
    public boolean isHollow() { return isHollow; }
    public double getVolume() { return Volume; }
    public double getMass() { return Mass; }
    public double getDensity() { return Density; }
    public Material getMaterial() { return material; }
    public Vector<Double, Double, Double> getPosition() { return Position; }
    public Vector<Double, Double, Double> getVelocity() { return Velocity; }
    public Vector<Double, Double, Double> getAcceleration() { return Acceleration; }


    // Basic 3D Shapes

    class Sphere extends Shape {
        double radius;
        public Sphere(double radius, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.radius = radius;
        }
        public double SurfaceArea() {
            return 4 * Math.PI * Math.pow(radius, 2);
        }
    }

    class Cube extends Shape {
        double side;
        public Cube(double side, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.side = side;
        }
        public double SurfaceArea() {
            return 6 * Math.pow(side, 2);
        }
        public double vortexAirResistanceConstant() {
            return 0; // There are no hole in the cube therefor vortexes are not formed internally
        }

    }

    class Cylinder extends Shape {
        double radius;
        double height;
        public Cylinder(double radius, double height, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.radius = radius;
            this.height = height;
        }
        public double SurfaceArea() {
            return 2 * Math.PI * radius * (radius + height);
        }
    }

    class Cone extends Shape {
        double radius;
        double height;
        public Cone(double radius, double height, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.radius = radius;
            this.height = height;
        }
        public double SurfaceArea() {
            return Math.PI * radius * (radius + Math.sqrt(Math.pow(height, 2) + Math.pow(radius, 2)));
        }
    }

    class Pyramid extends Shape {
        double base;
        double height;
        public Pyramid(double base, double height, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.base = base;
            this.height = height;
        }
        public double SurfaceArea() {
            return base * Math.sqrt(Math.pow(base / 2, 2) + Math.pow(height, 2));
        }
    }

    class RectangularPrism extends Shape {
        double length;
        double width;
        double height;
        public RectangularPrism(double length, double width, double height, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.length = length;            this.width = width;
            this.height = height;
        }
        public double SurfaceArea() {
            return 2 * (length * width + length * height + width * height);
        }
    }

    class TriangularPrism extends Shape {
        double base;
        double height;
        double side;
        public TriangularPrism(double base, double height, double side, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.base = base;
            this.height = height;
            this.side = side;
        }
        public double SurfaceArea() {
            return base * height + 2 * base * side + 2 * side * Math.sqrt(Math.pow(side, 2) + Math.pow(height, 2));
        }
    }

    class TrapezoidalPrism extends Shape {
        double base1;
        double base2;
        double height;
        double side;
        public TrapezoidalPrism(double base1, double base2, double height, double side, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.base1 = base1;
            this.base2 = base2;
            this.height = height;
            this.side = side;
        }
        public double SurfaceArea() {
            return base1 * height + base2 * height + 2 * side * Math.sqrt(Math.pow(side, 2) + Math.pow(height, 2));
        }
    }

    // Advanced 3D Shapes
    public static class IrregularPolyhedron extends Shape {
        public List<Vector<Double, Double, Double>> Vectors;

        public IrregularPolyhedron(List<Vector<Double, Double, Double>> Vectors, Double surfaces, boolean is3D, boolean isSmooth, boolean isPolygon, boolean isHollow, Double Mass, Double Volume, Double Density, Material material, Vector<Double, Double, Double> Position, Vector<Double, Double, Double> Velocity, Vector<Double, Double, Double> Acceleration) {
            super(surfaces, is3D, isSmooth, isPolygon, isHollow, Mass, Volume, Density, material, Position, Velocity, Acceleration);
            this.Vectors = Vectors;
        }

        public double GetSurfaceArea() {

            //Find vectors for vector A with the same end or start points
            Vector<Double, Double, Double> A = Vectors.getFirst();
            List<Vector<Double, Double, Double>> connectedVectors = new ArrayList<>();
            getConnection(A, Vectors, connectedVectors);
            List<Vector<Double, Double, Double>> Connections = new ArrayList<>();
            Connections.add(A);

            // Find the closest vectors to A to calculate the face
            boolean isConnected = false;
            while(!isConnected) {
                var closestConnector = findClosestVector(A, connectedVectors);
                Connections.add(closestConnector);
                // Update List of connected vectors.
                getConnection(closestConnector, Vectors, connectedVectors);
                if(closestConnector.getEnd() == A.point) isConnected = true;
            }

            /*
            * Now that the initial face has been found,
            * we need to remove the connection to a and repeat the process
            * so that we can get all faces connected to the A edge.
            * From there, we need to check if the shape has been connected.
            * If it has, then we can calculate the surface area.
            * If it has not, then we need to choose another edge vector to check,
            * however, this needs to be done programmatically to ensure
            * that all faces are covered and that none are skipped.
            * For this, we need to create a recursive function that will
            * check all faces and return the surface area.
            * For any given list of edges, there are only so many faces that can be found.
            * So a mathematical solution can be found to calculate
            * the total number of faces that need to be searched, and then they
            * can be used to calculate the surface area.
            * */

            return 0;
        }

        private void getConnection(Vector<Double, Double, Double> A , List<Vector<Double, Double, Double>> Vectors, List<Vector<Double, Double, Double>> connectedVectors) {
            for(Vector v : Vectors)
                if(v.point == A.point || v.getEnd() == A.point
                        || v.point == A.getEnd() || v.getEnd() == A.getEnd())
                    connectedVectors.add(v);
        }

        @SuppressWarnings("unchecked")
        public Vector<Double, Double, Double> findClosestVector(Vector<Double, Double, Double> A, List<Vector<Double, Double, Double>> ConnectedVectors) {
            List<Vector<Double, Double, Double>> R = new ArrayList<>();

            for(Vector V : ConnectedVectors)
                R.add(A.VectorSubtract(V));

            List<Double> T = new ArrayList<>();
            R.forEach((Vector<Double, Double, Double> v1) -> T.add(v1.getLength()));
            int index = T.indexOf(Collections.min(T));
            return ConnectedVectors.get(index);
        }
    }
}
