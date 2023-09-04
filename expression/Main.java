package expression;

public class Main {
    public static void main(String[] args) {
        Expression ex = new Multiply(
                new Subtract(new Variable("x"), new Const(1)),
                new Subtract(new Variable("x"), new Const(1))
        );
        System.out.println(ex.evaluate(Integer.parseInt(args[0])));
    }
}
