package finleycrowther.au.physicssim.Util;

import java.util.Arrays;
import java.util.Objects;

public class Utilities {

    public class Point3D {
        public Double x;
        public Double y;
        public Double z;
        Point3D(Double x, Double y, Double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Vector<X, Y, Z> {
        public X x;
        public Y y;
        public Z z;
        public Point3D point;

        public Vector(X x, Y y, Z z, Point3D point) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.point = point;
        }

        /**
         * Adds two vectors together
         * @param vectors the vectors to add
         * @return the sum of the vectors
         * Ċ = Ã + Ḃ*/
        Vector<Double, Double,Double> VectorAdd(Vector<Number, Number, Number>... vectors) {
            double xt = 0.0, yt = 0, zt = 0;
            for(Vector<Number, Number, Number> vector : vectors) {
                xt += (Double) vector.x;
                yt += (Double) vector.y;
                zt += (Double) vector.z;
            }
            return new Vector<>(xt, yt, zt, point);
        }

        /**
         * Subtracts two vectors together
         * @param vectors the vectors to subtract
         * @return the difference of the vectors
         * Ċ = Ã - Ḃ*/
        Vector<Double, Double,Double> VectorSubtract(Vector<Number, Number, Number>... vectors) {
            double xt = 0.0, yt = 0, zt = 0;
            for (Vector<Number, Number, Number> vector : vectors) {
                xt -= (Double) vector.x;
                yt -= (Double) vector.y;
                zt -= (Double) vector.z;
            }
            return new Vector<>(xt, yt, zt, point);
        }

        /**
         * Returns the dot product of two vectors
         * @param vectors the vectors to multiply
         * @return the dot product of the vectors
         * Ċ = Ã • Ḃ*/
        Double VectorDotProduct(Vector<Number, Number, Number>... vectors) {
            double xt = 0.0, yt = 0, zt = 0;
            for (Vector<Number, Number, Number> vector : vectors) {
                xt *= (Double) vector.x;
                yt *= (Double) vector.y;
                zt *= (Double) vector.z;
            }
            return xt + yt + zt;
        }

        /**
         * Returns the division sum of two vectors
         * @param vectors the vectors to multiply
         * @return the division sum of the vectors
         * Ċ = Ã / Ḃ*/
        Vector<Double, Double,Double> VectorDivide(Vector<Number, Number, Number>... vectors) {
            double xt = 0.0, yt = 0, zt = 0;
            for (Vector<Number, Number, Number> vector : vectors) {
                xt /= (Double) vector.x;
                yt /= (Double) vector.y;
                zt /= (Double) vector.z;
            }
            return new Vector<>(xt, yt, zt, point);
        }

        /**
         * Scales a vector based of a value*/
        Vector<X, Y, Z> getScalar(double scalar) {
            if(instanceOfNumeric(x,y,z)) throw new IllegalArgumentException("All x, y and z must be of type Number");
            return null;
        }

        /**
         * Returns the length of the vector
         * @return the length of the vector
         * len = |Ã| (√i²+j²...)*/
        Double getLength() {
            if(instanceOfNumeric(x,y,z)) throw new IllegalArgumentException("All x, y and z must be of type Number");
            return Math.sqrt(Math.pow((Double) x, 2) + Math.pow((Double) y, 2) + Math.pow((Double) z, 2));
        }

        /**
         * Returns the unit vector of the vector
         * @return the unit vector of the vector
         * unit Ậ = Ã/|Ã|*/
        Vector<X, Y, Z> getUnitVector() {
            if(instanceOfNumeric(x,y,z)) throw new IllegalArgumentException("All x, y and z must be of type Number");
            return null;
        }

        /**
         * Checks that both X & Y are instances of a numeric value e.g. Double, Float, Int etc
         * @see Number
         * @return false if all objects are numbers else true. */
        private boolean instanceOfNumeric(Object... o) {
            return Arrays.stream(o).noneMatch(e -> e instanceof Number);
        }
    }
}
