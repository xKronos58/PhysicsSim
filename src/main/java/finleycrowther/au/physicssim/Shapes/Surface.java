package finleycrowther.au.physicssim.Shapes;

public abstract class Surface {

    public Surface() {

    }

    public Surface getSurface() {
        return this;
    }

    static class Square extends Surface {
        public Square() {
            super();
        }
    }

    static class Circle extends Surface {
        public Circle() {
            super();
        }
    }

    static class Rectangle extends Surface {
        public Rectangle() {
            super();
        }
    }

    static class Triangle extends Surface {
        public Triangle() {
            super();
        }
    }

    static class Polygon extends Surface {
        public Polygon() {
            super();
        }
    }


}
