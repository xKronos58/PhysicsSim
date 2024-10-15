package finleycrowther.au.physicssim.AirSimulation;

import finleycrowther.au.physicssim.Shapes.Shape;

public class AirFlow {
    Shape shape;
    double flowRate;
    double frictionCoefficient;

    public AirFlow(Shape shape, double flowRate, double frictionCoefficient) {
        this.shape = shape;
        this.flowRate = flowRate;
        this.frictionCoefficient = frictionCoefficient;
    }

    public void getResistance() {

    }

    public double getImpactAngle() {
        return 0;
    }

    public Shape getInputSurface() {
        return shape;
    }
}
