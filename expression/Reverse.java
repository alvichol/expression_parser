package expression;

public class Reverse implements MultiExpression {
    private final MultiExpression exp;

    public Reverse(MultiExpression exp) {
        this.exp = exp;
    }

    @Override
    public int getPrior() {
        return 0;
    }

    @Override
    public double evaluate(double x) {
        return exp.evaluate(x);
    }

    private int reverse(int x) {
        long coeff = 1;
        long temp = x;
        if (x < 0) {
            temp *= -1;
            coeff = -1;
        }
        StringBuilder sb = new StringBuilder();
        while (temp > 0) {
            sb.append(temp % 10);
            temp /= 10;
        }
        if (sb.toString().equals("")) {
            sb.append(0);
        }
        return (int)(coeff * Long.parseLong(sb.toString()));
    }

    @Override
    public int evaluate(int x) {
        return reverse(exp.evaluate(x));
    }

    @Override
    public String toMiniString() {
        if (exp.getPrior() > getPrior()) {
            return "reverse(" + exp.toMiniString() + ")";
        }
        return "reverse " + exp.toMiniString();
    }

    @Override
    public String toString() {
        return "reverse(" + exp.toString() + ")";
    }

    @Override
    public int evaluate(int x, int y, int z) {
        //System.out.println(exp.evaluate(x, y, z));
        return reverse(exp.evaluate(x, y, z));
    }
}
