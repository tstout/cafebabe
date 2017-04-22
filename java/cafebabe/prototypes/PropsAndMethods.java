package cafebabe.prototypes;

public class PropsAndMethods {
    public final int intField;
    public final String strField;
    public final double doubleField;
    public final float floatField;
    public final byte byteField;

    public PropsAndMethods(
            int intField,
            String strField,
            double doubleField,
            float floatField,
            byte byteField) {
        this.intField = intField;
        this.strField = strField;
        this.doubleField = doubleField;
        this.floatField = floatField;
        this.byteField = byteField;
    }

    public int intField() {
        return intField;
    }

    public String strField() {
        return strField;
    }

    public double doubleField() {
        return doubleField;
    }

    public float floatField() {
        return floatField;
    }

    public byte returnByteField() {
        return byteField;
    }
}
