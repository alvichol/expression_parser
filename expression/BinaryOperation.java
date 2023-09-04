package expression;

public abstract class BinaryOperation implements MultiExpression {
    protected MultiExpression first;
    protected MultiExpression second;

    public String toString(String sym) {
        return "(" + first.toString() + " " + sym + " " + second.toString() + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        BinaryOperation that = (BinaryOperation) obj;
        return this.first.equals(that.first) && this.second.equals(that.second);
    }

    abstract int calc(int a, int b);

    abstract double calc(double a, double b);

    @Override
    public int evaluate(int val) {
        return calc(first.evaluate(val), second.evaluate(val));
    }

    @Override
    public double evaluate(double val) {
        return calc(first.evaluate(val), second.evaluate(val));
    }

    @Override
    public int evaluate(int val1, int val2, int val3) {
        return calc(first.evaluate(val1, val2, val3), second.evaluate(val1, val2, val3));
    }

    public String toMiniString(String sym) {
        StringBuilder sb = new StringBuilder();
        if (first.getPrior() > getPrior()) {
            sb.append("(");
            sb.append(first.toMiniString());
            sb.append(")");
        } else {
            sb.append(first.toMiniString());
        }
        sb.append(sym);
        if (second.getPrior() > getPrior() ||
            second.getPrior() == getPrior() && this instanceof Divide ||
            second.getPrior() == getPrior() && this instanceof Subtract ||
            this instanceof Multiply && second instanceof Divide ||
            this instanceof Gcd && second instanceof Lcm ||
            this instanceof Lcm && second instanceof Gcd) {
            sb.append("(");
            sb.append(second.toMiniString());
            sb.append(")");
        } else {
            sb.append(second.toMiniString());
        }
        return sb.toString();
    }
}
