
public class Weight {

    // Instance variables
    private double value;
    private WeightUnit unit;

    // Constructor
    public Weight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    // Getter
    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    // ================= EQUALITY =================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weight)) return false;

        Weight other = (Weight) o;
        return compare(other);
    }

    // Compare using base unit (grams)
    private boolean compare(Weight thatWeight) {
        double thisBase = convertToBaseUnit();
        double otherBase = thatWeight.convertToBaseUnit();

        return Double.compare(thisBase, otherBase) == 0;
    }

    // ================= CONVERSION =================
    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(convertedValue, targetUnit);
    }

    // ================= ADDITION =================

    // UC6 style (result in same unit as first operand)
    public Weight add(Weight thatWeight) {
        return add(thatWeight, this.unit);
    }

    // UC7 style (explicit target unit)
    public Weight add(Weight weight, WeightUnit targetUnit) {

        if (weight == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        return addAndConvert(weight, targetUnit);
    }

    // ================= INTERNAL METHODS =================

    // Convert to base (grams)
    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // Core addition logic
    private Weight addAndConvert(Weight weight, WeightUnit targetUnit) {

        double thisBase = this.convertToBaseUnit();
        double otherBase = weight.convertToBaseUnit();

        double sumBase = thisBase + otherBase;

        double resultValue = convertFromBaseToTargetUnit(sumBase, targetUnit);

        return new Weight(resultValue, targetUnit);
    }

    // Base → target conversion
    private double convertFromBaseToTargetUnit(double weightInGrams,
                                               WeightUnit targetUnit) {

        return targetUnit.convertFromBaseUnit(weightInGrams);
    }

    // ================= STRING =================
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }

    @Override
    public int hashCode() {
        return Double.valueOf(convertToBaseUnit()).hashCode();
    }
}