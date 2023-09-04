package expression.parser;

public enum ExpressionElement {
    ADD(2),
    SUB(2),
    CONST(0),
    VAR(0),
    MUL(1),
    DIV(1),
    LB(0),
    RB(0),
    EOF(2),
    WS(2),
    GCD(3),
    LCM(3),
    REV(0);

    public final int prior;
    private ExpressionElement(int prior) {
        this.prior = prior;
    }
}
