package expression.parser;

import expression.TripleExpression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите выражение");
            TripleParser parser = new ExpressionParser();
            String exp = in.nextLine();
            TripleExpression expression = parser.parse(exp);
            System.out.println("Введите переменные x, y, z");
            System.out.flush();
            int x, y, z;
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            System.out.println("Результат выражения: " + expression.evaluate(x, y, z));
            System.out.println("To string: " + expression.toString());
            System.out.println("To mini string: " + expression.toMiniString());
        } catch (Exception e) {
            System.out.println("Некорректный ввод");
        }
    }
}
