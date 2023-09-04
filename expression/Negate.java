package expression;

public class Negate implements MultiExpression {
    private final MultiExpression exp;

    public Negate(MultiExpression exp) {
        this.exp = exp;
    }

    @Override
    public int getPrior() {
        return 0;
    }

    @Override
    public double evaluate(double x) {
        return exp.evaluate(x) * (-1);
    }

    @Override
    public int evaluate(int x) {
        return exp.evaluate(x) * (-1);
    }

    @Override
    public String toMiniString() {
        if (exp.getPrior() > getPrior()) {
            return "-(" + exp.toMiniString() + ")";
        }
        return "- " + exp.toMiniString();
    }

    @Override
    public String toString() {
        return "-(" + exp.toString() + ")";
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return exp.evaluate(x, y, z) * (-1);
    }
}
