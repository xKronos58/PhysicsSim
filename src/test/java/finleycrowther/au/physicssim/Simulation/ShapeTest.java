package finleycrowther.au.physicssim.Simulation;

import finleycrowther.au.physicssim.Shapes.Material;
import finleycrowther.au.physicssim.Shapes.Shape;
import finleycrowther.au.physicssim.Util.Utilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    Utilities.Vector<Double, Double, Double> emptyVector = new Utilities.Vector<>(0.0, 0.0, 0.0, new Utilities.Point3D(0.0, 0.0, 0.0));

    @Test
    void surfaceArea() {
        finleycrowther.au.physicssim.Shapes.Shape shape = new finleycrowther.au.physicssim.Shapes.Shape.IrregularPolyhedron(
                List.of(
                        new Utilities.Vector<>(1.0, 1.0, 1.0, new Utilities.Point3D(0.0, 0.0, 0.0)),
                        new Utilities.Vector<>(-1.0, 1.0, 0.0, new Utilities.Point3D(1.0, 1.0, 1.0)),
                        new Utilities.Vector<>(-1.0, -1.0, 1.0, new Utilities.Point3D(1.0, 1.0, 1.0))
                ), 3.0, true, true, true, false, 1.0, 1.0, 1.0, Material.WOOD,
                emptyVector, emptyVector, emptyVector
        );
        assertEquals(0, shape.SurfaceArea());
    }

    @Test
    void findClosestVector() {
        finleycrowther.au.physicssim.Shapes.Shape.IrregularPolyhedron shape = new Shape.IrregularPolyhedron(
                List.of(
                        new Utilities.Vector<>(1.0, 1.0, 1.0, new Utilities.Point3D(0.0, 0.0, 0.0)),
                        new Utilities.Vector<>(-1.0, 1.0, 0.0, new Utilities.Point3D(0.0, 0.0, 0.0)),
                        new Utilities.Vector<>(-1.0, -1.0, 1.0, new Utilities.Point3D(0.0, 0.0, 0.0))
                ),3.0, true, true, true, false, 1.0, 1.0, 1.0, Material.WOOD,
                emptyVector, emptyVector, emptyVector
        );

        Utilities.Vector<Double, Double, Double> result = shape.findClosestVector(new Utilities.Vector<>(1.0, 1.0, 1.0, new Utilities.Point3D(-1.0, -1.0, -1.0)), shape.Vectors);
        Utilities.Vector<Double, Double, Double> expected = new Utilities.Vector<>(-1.0, 1.0, 0.0, new Utilities.Point3D(0.0, 0.0, 0.0));

        assertEquals(result.x,expected.x);
        assertEquals(result.y,expected.y);
        assertEquals(result.z,expected.z);
    }
}