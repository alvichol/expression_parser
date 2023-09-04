package expression;

public interface MultiExpression extends Expression, TripleExpression, DoubleExpression {
    int getPrior();
}
