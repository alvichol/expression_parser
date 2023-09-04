package expression;

public class Const implements MultiExpression {
    private final Number val;
    public Const(int val) {
        this.val = val;
    }

    public Const(double val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public int getPrior() {
        return 0;
    }

    @Override
    public int evaluate(int val) {
        return this.val.intValue();
    }

    @Override
    public double evaluate(double val) {
        return this.val.doubleValue();
    }

    @Override
    public int evaluate(int val1, int val2, int val3) {
        return (int)this.val;
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
        Const that = (Const) obj;
        return this.val.equals(that.val);
    }
}
