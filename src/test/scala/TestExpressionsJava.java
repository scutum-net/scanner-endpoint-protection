
public class TestExpressionsJava {
    public class SomeError extends Throwable {}

    private void f() throws SomeError {
        throw new SomeError();
    }
}
