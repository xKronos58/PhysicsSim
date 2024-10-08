package finleycrowther.au.physicssim.Util;

import finleycrowther.au.physicssim.Simulation.Object;

import java.util.*;

public class Utilities {

    @SuppressWarnings("unchecked") // Will be an instance of *Number*
    public static void QuickSort(List<Double> r, int low, int high) {
        if(r.isEmpty() || r.size() == 1) return;
//        assert r instanceof Number;
//        Number Min = GetMin((List<Number>) r), Max = GetMax((List<Number>) r);

        if(low < high) {
            int PartitionIndex = Partition((List<Double>) r, low, high);
            QuickSort(r, low, PartitionIndex - 1);  // Left part
            QuickSort(r, PartitionIndex + 1, high); // Right part
        }
    }

    private static int Partition(List<Double> list, int min, int max) {
        double pivot = list.get(max);  // Choose the last element as the pivot
        int i = min - 1;  // Index of the smaller element

        // Traverse through the list and rearrange elements
        for (int j = min; j < max; j++) {
            if (list.get(j) < pivot) {
                i++;
                // Swap list[i] and list[j]
                Double temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Swap list[i + 1] and list[max] (or pivot)
        Double temp = list.get(i + 1);
        list.set(i + 1, list.get(max));
        list.set(max, temp);

        return i + 1;  // Return the partition index
    }

    private static Number GetMin(List<Number> r) {
        return Collections.min(r, Comparator.comparingDouble(Number::doubleValue));
    }

    private static Number GetMax(List<Number> r) {
        return Collections.max(r, Comparator.comparingDouble(Number::doubleValue));
    }

    public static class Point3D {
        public Double x;
        public Double y;
        public Double z;
        public Point3D(Double x, Double y, Double z) {
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
        public Vector<Double, Double,Double> VectorSubtract(Vector<Number, Number, Number>... vectors) {
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
        public Double getLength() {
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
        private boolean instanceOfNumeric(java.lang.Object... o) {
            return Arrays.stream(o).noneMatch(e -> e instanceof Number);
        }

        Double getTotal() {
            return (Double) x + (Double) y + (Double) z;
        }

        public Point3D getEnd() {
            return new Point3D(point.x + (Double) x, point.y + (Double) y, point.z + (Double) z);
        }
    }
}
