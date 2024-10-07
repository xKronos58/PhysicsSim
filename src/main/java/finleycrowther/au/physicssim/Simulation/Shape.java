package finleycrowther.au.physicssim.Simulation;

import finleycrowther.au.physicssim.Util.Utilities;
import finleycrowther.au.physicssim.Util.Utilities.Vector;

public abstract class Shape {
    Double surfaces;
    boolean is3D;
    boolean isSmooth;
    boolean isPolygon;
    boolean isHollow;



    public Shape() {

    }

    public double SurfaceArea() { return 0; }



    // Basic 3D Shapes

    class Sphere extends Shape {
        double radius;
        public Sphere(double radius) {
            this.radius = radius;
        }
        public double SurfaceArea() {
            return 4 * Math.PI * Math.pow(radius, 2);
        }
    }

    class Cube extends Shape {
        double side;
        public Cube(double side) {
            this.side = side;
        }
        public double SurfaceArea() {
            return 6 * Math.pow(side, 2);
        }
    }

    class Cylinder extends Shape {
        double radius;
        double height;
        public Cylinder(double radius, double height) {
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
        public Cone(double radius, double height) {
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
        public Pyramid(double base, double height) {
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
        public RectangularPrism(double length, double width, double height) {
            this.length = length;
            this.width = width;
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
        public TriangularPrism(double base, double height, double side) {
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
        public TrapezoidalPrism(double base1, double base2, double height, double side) {
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

    public class IrregularPolyhedron extends Shape {
        Vector<Double, Double, Double>[] vertices;
        Vector<Double, Double, Double>[] edges;
    }
}
