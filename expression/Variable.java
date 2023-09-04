package expression;

public class Variable implements MultiExpression {
    private final String txt;
    public Variable(String txt) {
        this.txt = txt;
    }
    @Override
    public int evaluate(int val) {
        return val;
    }

    @Override
    public double evaluate(double val) {
        return val;
    }

    @Override
    public int evaluate(int val1, int val2, int val3) {
        if (txt.equals("x")) {
            return val1;
        }
        if (txt.equals("y")) {
            return val2;
        }
        if (txt.equals("z")) {
            return val3;
        }
        return 0;
    }

    @Override
    public String toString() {
        return txt;
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
        Variable that = (Variable) obj;
        return this.txt.equals(that.txt);
    }

    @Override
    public int getPrior() {
        return 0;
    }
}
