package cafebabe.prototypes;

public class PropsAndMethods {
    public final int intField;
    public final String strField;
    public final double doubleField;
    public final float floatField;
    public final byte byteField;
    public final byte[] byteArrayField;

    public PropsAndMethods() {
        intField = 0;
        strField = "";
        doubleField = 0.0;
        floatField = 0.0f;
        byteField = 'c';
        byteArrayField = new byte[]{};
    }


    public PropsAndMethods(
            int intField,
            String strField,
            double doubleField,
            float floatField,
            byte byteField,
            byte[] byteArrayField) {
        this.intField = intField;
        this.strField = strField;
        this.doubleField = doubleField;
        this.floatField = floatField;
        this.byteField = byteField;
        this.byteArrayField = byteArrayField;
    }

    public int intFieldValue() {
        return intField;
    }

    public String strFieldValue() {
        return strField;
    }

    public double doubleFieldValue() {
        return doubleField;
    }

    public float floatFieldValue() {
        return floatField;
    }

    public byte returnByteFieldValue() {
        return byteField;
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public void invokeOtherClass() {
        new HasThrowingMethod().foo(true);
    }
}
