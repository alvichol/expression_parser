package expression;

public class Lcm extends BinaryOperation {
    public Lcm(MultiExpression first, MultiExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return toString("lcm");
    }

    @Override
    public String toMiniString() {
        return toMiniString(" lcm ");
    }

    @Override
    public int getPrior() {
        return 3;
    }

    @Override
    public int calc(int a, int b) {
        long mul = (long) a * (long) b;
        if (a < 0) {
            a *= -1;
        }
        if (b < 0) {
            b *= -1;
        }
        while (a != 0) {
            b %= a;
            int temp = b;
            b = a;
            a = temp;
        }
        if (b == 0) {
            return 0;
        }
        return (int) (mul / (long) b);
    }

    @Override
    public double calc(double a, double b) {
        return 0;
    }
}
