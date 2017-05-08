package cafebabe.prototypes;

public class HasThrowingMethod {
    public void foo(boolean shouldThrow) throws IllegalArgumentException {
        if (shouldThrow) {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
