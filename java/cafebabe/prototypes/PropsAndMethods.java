package cafebabe.prototypes;

import java.util.stream.Stream;

public class PropsAndMethods {
    public final int intField;
    public final String strField;
    public final double doubleField;
    public final float floatField;
    public final byte byteField;
    public final boolean boolField;
    public final byte[] byteArrayField;

    public PropsAndMethods() {
        boolField = true;
        intField = 0;
        strField = "";
        doubleField = 0.0;
        floatField = 0.0f;
        byteField = 'c';
        byteArrayField = new byte[] {};
    }

    public PropsAndMethods(
            int intField,
            String strField,
            double doubleField,
            float floatField,
            byte byteField,
            boolean boolField,
            byte[] byteArrayField
            ) {
        this.intField = intField;
        this.strField = strField;
        this.doubleField = doubleField;
        this.floatField = floatField;
        this.byteField = byteField;
        this.boolField = boolField;
        this.byteArrayField = byteArrayField;
    }

    public boolean boolFieldValue() {
        return boolField;
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

    public byte[] byteArrayField() {
        return byteArrayField;
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public String stream() {
        return Stream.of("a", "b", "c", "d")
                .filter(i -> i.contains("x"))
                .findFirst()
                .orElse("Not Found");
    }

    public int conditional(int a, int b, int c, int d) {
        if (a > b) {
            return a - b;
        }

        if (a == b) {
            return a + b;
        }

        if (c == d) {
            return c + d;
        }

        throw new RuntimeException("fallthrough");
    }

    public void invokeOtherClass() {
        new HasThrowingMethod().foo(true);
    }

//    public void callProbe() {
//        cafebabe.Probe.onEntry("test val");
//    }
}
