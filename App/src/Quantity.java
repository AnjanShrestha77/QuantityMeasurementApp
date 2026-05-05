/**
 * Generic Quantity class representing a measurable value with unit
 *
 * @param <U> type of unit extending IMeasurable
 */
public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    // ================= CONSTRUCTOR =================
    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ================= CONVERSION =================
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    // ================= EQUALITY =================
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!this.unit.getClass().equals(other.getUnit().getClass())) {
            return false;
        }

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.getUnit().convertToBaseUnit(other.getValue());

        return Math.abs(thisBase - otherBase) < 0.01;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        long rounded = Math.round(base * 100);
        return Long.hashCode(rounded);
    }

    // ================= UC13 CORE =================

    // 🔥 ENUM FOR OPERATIONS
    private enum ArithmeticOperation {
        ADD {
            public double compute(double a, double b) { return a + b; }
        },
        SUBTRACT {
            public double compute(double a, double b) { return a - b; }
        },
        DIVIDE {
            public double compute(double a, double b) {
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        public abstract double compute(double a, double b);
    }

    // 🔥 CENTRAL VALIDATION
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean isTargetRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (!this.unit.getClass().equals(other.getUnit().getClass())) {
            throw new IllegalArgumentException("Cannot operate on different measurement categories");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(other.getValue())) {
            throw new IllegalArgumentException("Values must be finite");
        }

        if (isTargetRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    // 🔥 CORE HELPER
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.getUnit().convertToBaseUnit(other.getValue());

        return op.compute(base1, base2);
    }

    // ================= ADD =================
    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, this.unit, false);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = this.unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }

    // ================= SUBTRACT =================
    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, this.unit, false);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = this.unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }

    // ================= DIVIDE =================
    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }
}