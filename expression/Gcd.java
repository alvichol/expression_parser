package expression;

public class Gcd extends BinaryOperation {
    public Gcd(MultiExpression first, MultiExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return toString("gcd");
    }

    @Override
    public String toMiniString() {
        return toMiniString(" gcd ");
    }

    @Override
    public int getPrior() {
        return 3;
    }

    @Override
    public int calc(int a, int b) {
        while (a != 0) {
            b %= a;
            int temp = b;
            b = a;
            a = temp;
        }
        if (b < 0) {
            return b * (-1);
        }
        return b;
    }

    @Override
    public double calc(double a, double b) {
        return 0;
    }
}
