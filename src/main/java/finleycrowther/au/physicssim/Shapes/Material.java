package finleycrowther.au.physicssim.Shapes;

public enum Material {
    WOOD(0.5),
    METAL(1.0),
    RUBBER(0.3),
    GLASS(0.8),
    PLASTIC(0.4),
    STONE(1.5),
    WATER(0.2),
    ICE(0.1),
    DIAMOND(2.4),
    AIR(0.0);

    private final double friction;

    Material(double friction) {
        this.friction = friction;
    }

    public double getFriction() {
        return friction;
    }
}
