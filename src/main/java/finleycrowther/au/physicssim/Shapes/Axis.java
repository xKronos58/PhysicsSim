package finleycrowther.au.physicssim.Shapes;

public class Axis<X, Y, Z, RefAxis> {
    X x;
    Y y;
    Z z;
    RefAxis refAxis;

    enum AxisType {
        X,
        Y,
        Z
    }

    public Axis(X x, Y y, Z z, RefAxis refAxis) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.refAxis = refAxis;
    }

    AxisType getAxis() {
        return null;
    }
    
    double getClosestAngle() {
        return 0.0;
    }
}
