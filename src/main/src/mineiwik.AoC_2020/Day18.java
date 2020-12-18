package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/*
 * Advent of Code 2020 - Day 18 - Operation Order
 */
public class Day18 extends Day {

    private final String[] expressions;
    private final ArrayList<Character> operators;

    Day18(String day) throws IOException {
        super(day);
        expressions = input.replaceAll(" ", "").split("\\r?\\n");

        operators = new ArrayList<>();
        operators.add('+');
        operators.add('*');
    }

    public String firstStar() {
        long sum = 0;
        for (String expression : expressions) {
            Stack<Character> postfix = infixToPostfix(expression, new int[]{1, 1});
            sum += calculatePostfix(postfix);
        }
        return String.valueOf(sum);
    }

    public String secondStar() {
        long sum = 0;
        for (String expression : expressions) {
            Stack<Character> postfix = infixToPostfix(expression, new int[]{2, 1});
            sum += calculatePostfix(postfix);
        }
        return String.valueOf(sum);
    }

    // Using the Shunting-yard algorithm
    public Stack<Character> infixToPostfix (String expression, int[] precedence) {
        Stack<Character> operator = new Stack<>();
        Stack<Character> output = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (Character.isDigit(current)) {
                output.push(current);
            } else if (operators.contains(current)) {
                while (!operator.empty()
                        && operator.peek() != '('
                        && (precedence[operators.indexOf(operator.peek())] > precedence[operators.indexOf(current)]
                            || precedence[operators.indexOf(operator.peek())] == precedence[operators.indexOf(current)])) {
                    output.push(operator.pop());
                }
                operator.push(current);
            } else if (current == '(') {
                operator.push(current);
            } else if (current == ')') {
                while (operator.peek() != '(') {
                    output.push(operator.pop());
                }
                if (operator.peek() == '(') {
                    operator.pop();
                }
            }
        }
        while (!operator.empty()) {
            output.push(operator.pop());
        }

        Stack<Character> upsideDown = new Stack<>();
        while (!output.empty()) upsideDown.push(output.pop());
        return upsideDown;
    }

    public long calculatePostfix (Stack<Character> input) {
        Stack<Long> temp = new Stack<>();

        while(!input.empty()) {
            if (Character.isDigit(input.peek())) {
                temp.push((long) (input.pop() - 48));
            }
            else if (operators.contains(input.peek())){
                char operand = input.pop();
                switch (operand) {
                    case'+':
                        temp.push(temp.pop() + temp.pop());
                        break;
                    case'*':
                        temp.push(temp.pop() * temp.pop());
                        break;
                }
            }
        }
        return temp.pop();
    }


}