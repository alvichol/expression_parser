package expression.parser;

import expression.*;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser implements TripleParser {
    private int index;
    private final Map<String, ExpressionElement> words = Map.of(
            "x", ExpressionElement.VAR,
            "y", ExpressionElement.VAR,
            "z", ExpressionElement.VAR,
            "reverse", ExpressionElement.REV,
            "gcd", ExpressionElement.GCD,
            "lcm", ExpressionElement.LCM
    );

    @Override
    public TripleExpression parse(String expression) {
        index = expression.length() - 1;
        return priorParse(expression, 3);
    }

    private ExpressionElement getElement(String expression, int idx) {
        if (idx < 0) {
            return ExpressionElement.EOF;
        }

        if (Character.isDigit(expression.charAt(idx))) {
            return ExpressionElement.CONST;
        }

        if (Character.isLetter(expression.charAt(idx))) {
            for (Map.Entry<String, ExpressionElement> entry : words.entrySet()) {
                int len = entry.getKey().length();
                if (idx >= len && !Character.isDigit(expression.charAt(idx - len)) || idx == len - 1) {
                    if (expression.substring(idx - len + 1, idx + 1).equals(entry.getKey())) {
                        return entry.getValue();
                    }
                }
            }
        }

        return switch (expression.charAt(idx)) {
            case '(' -> ExpressionElement.LB;
            case ')' -> ExpressionElement.RB;
            case '+' -> ExpressionElement.ADD;
            case '*' -> ExpressionElement.MUL;
            case '/' -> ExpressionElement.DIV;
            case '-' -> ExpressionElement.SUB;
            default -> ExpressionElement.WS;
        };
    }

    private void skipWhitespace(String expression) {
        while (getElement(expression, index) == ExpressionElement.WS) {
            index--;
        }
    }

    private boolean negateConst(MultiExpression exp) {
        return (exp instanceof Const) &&
                !(exp.evaluate(0, 0, 0) == 0);
    }

    private boolean checkSub(String expression) {
        int j = index;
        index--;
        skipWhitespace(expression);

        if (getElement(expression, index) == ExpressionElement.VAR ||
                getElement(expression, index) == ExpressionElement.CONST ||
                getElement(expression, index) == ExpressionElement.RB) {
            index = j;
            return true;
        }
        return false;
    }

    private MultiExpression getUnaryOps(String expression, MultiExpression result, boolean bracket) {
        boolean separated = (getElement(expression, index) == ExpressionElement.WS);
        skipWhitespace(expression);

        if (getElement(expression, index) == ExpressionElement.LB) {
            if (!bracket) {
                return result;
            }
            separated = true;
            index--;
        }

        while (true) {
            skipWhitespace(expression);
            switch (getElement(expression, index)) {
                case REV -> {
                    index -= 7;
                    result = new Reverse(result);
                }
                case SUB -> {
                    if (checkSub(expression)) {
                        return result;
                    }
                    if (!separated && !bracket && negateConst(result)) {
                        result = new Const((result).evaluate(0) * (-1));
                        separated = true;
                    } else {
                        result = new Negate(result);
                    }

                }
                default -> {
                    return result;
                }
            }
        }
    }

    private MultiExpression priorParse(String expression, int prior) {
        MultiExpression result = null;
        skipWhitespace(expression);
        if (prior == 0) {
            boolean bracket = false;

            switch (getElement(expression, index)) {
                case RB -> {
                    index--;
                    bracket = true;
                    result = priorParse(expression, 3);
                } case CONST -> {
                    int j = index;
                    while (getElement(expression, j) == ExpressionElement.CONST) {
                        j--;
                    }
                    result = new Const(Integer.parseUnsignedInt(expression.substring(j + 1, index + 1)));
                    index = j;
                } case VAR -> {
                    result = new Variable(expression.substring(index, index + 1));
                    index--;
                }
            }

            result = getUnaryOps(expression, result, bracket);
            return result;
        }

        MultiExpression first, second;
        second = priorParse(expression, prior - 1);

        skipWhitespace(expression);
        if (getElement(expression, index) == ExpressionElement.LB ||
                getElement(expression, index) == ExpressionElement.EOF) {
            return second;
        }
        if (getElement(expression, index).prior > prior) {
            return second;
        }

        ExpressionElement el = getElement(expression, index);
        index--;
        if (el == ExpressionElement.GCD || el == ExpressionElement.LCM) {
            index -= 2;
        }
        first = priorParse(expression, prior);

        return switch(el) {
            case MUL -> new Multiply(first, second);
            case ADD -> new Add(first, second);
            case SUB -> new Subtract(first, second);
            case DIV -> new Divide(first, second);
            case GCD -> new Gcd(first, second);
            case LCM -> new Lcm(first, second);
            default -> null;
        };
    }
}
