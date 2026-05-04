

public class Length {

    private double value;
    private LengthUnit unit;

    // Constructor
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    // 🔥 FIX (for your test error)
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // ================= EQUALITY =================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;

        Length other = (Length) o;
        return compare(other);
    }

    private boolean compare(Length other) {
        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(unit.convertToBaseUnit(value)).hashCode();
    }

    // ================= CONVERSION =================
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Length(converted, targetUnit);
    }

    // ================= ADDITION (UC6) =================
    public Length add(Length other) {
        return add(other, this.unit);
    }

    // ================= ADDITION WITH TARGET (UC7) =================
    public Length add(Length other, LengthUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        return addAndConvert(other, targetUnit);
    }

    private Length addAndConvert(Length other, LengthUnit targetUnit) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Length(result, targetUnit);
    }

    // ================= STRING =================
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}