package cafebabe.prototypes;

public class PropsOnly {
    public final int intField;
    public final String strField;
    public final double doubleField;
    public final float floatField;
    public final byte byteField;

    public PropsOnly(
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
}
