package expression;

public class Divide extends BinaryOperation {
    public Divide(MultiExpression first, MultiExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return toString("/");
    }

    @Override
    public String toMiniString() {
        return toMiniString(" / ");
    }

    @Override
    public int getPrior() {
        return 1;
    }

    @Override
    public int calc(int a, int b) {
        return a / b;
    }

    @Override
    public double calc(double a, double b) {
        return a / b;
    }
}
