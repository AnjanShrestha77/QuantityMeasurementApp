/**
 * Generic Quantity class representing a measurable value with unit
 *
 * @param <U> type of unit extending IMeasurable
 */
public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    /**
     * Constructor with validation
     */
    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
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

    /**
     * Converts this quantity to target unit
     */
    public double convertTo(U targetUnit) {
        double baseValue = unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    /**
     * Add and return in current unit
     */
    public Quantity<U> add(Quantity<U> other) {
        double totalBase = this.unit.convertToBaseUnit(this.value)
                + other.unit.convertToBaseUnit(other.value);

        double finalValue = this.unit.convertFromBaseUnit(totalBase);
        return new Quantity<>(finalValue, this.unit);
    }

    /**
     * Add and return in target unit
     */
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double totalBase = this.unit.convertToBaseUnit(this.value)
                + other.unit.convertToBaseUnit(other.value);

        double finalValue = targetUnit.convertFromBaseUnit(totalBase);
        return new Quantity<>(finalValue, targetUnit);
    }

    /**
     * Equality based on base unit comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other)) {
            return false;
        }

        // 🚨 Prevent cross-category comparison (IMPORTANT)
        if (!this.unit.getClass().equals(other.getUnit().getClass())) {
            return false;
        }

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.getUnit().convertToBaseUnit(other.getValue());

        return Math.abs(thisBase - otherBase) < 0.01;
    }

    /**
     * HashCode consistent with equals
     */
    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        long rounded = Math.round(baseValue * 100); // match equals precision
        return Long.hashCode(rounded);
    }
}