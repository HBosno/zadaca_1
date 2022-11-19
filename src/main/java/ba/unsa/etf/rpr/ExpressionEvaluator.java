package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * Class implementing Dijkstra's Algorithm for expression evaluation
 */
public class ExpressionEvaluator {
    /**
     * Evaluates previously validated arithmetic expression in String format using Dijkstra's Algorithm
     * @param expression - String type containing the arithmetic expression to be evaluated
     * @return - Double type result of the arithmetic expression represented by above String parameter
     */
    public Double evaluate(String expression){

        Stack<String> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();

        /*  We will use the fact that the expression is already validated;
            that is, it contains exactly the following: operators, operands and parentheses
            separated by single whitespace characters, to implement this section with relative ease. */

        String[] substrings = expression.split(" ");

        for(String s:substrings){
            switch(s){
                case "(":
                    break;
                case "+":
                    operators.push(s);
                    break;
                case "-":
                    operators.push(s);
                    break;
                case "*":
                    operators.push(s);
                    break;
                case "/":
                    operators.push(s);
                    break;
                case "sqrt":
                    operators.push(s);
                    break;
                case ")":
                    String operator = operators.pop();
                    Double operand = operands.pop();
                    operand = operandAccordingToOperator(operator, operand, operands);
                    operands.push(operand);
                    break;
                default:
                    operands.push(Double.parseDouble(s));
            }
        }

        return operands.pop();
    }

    /**
     * Added solely for better code readability, to avoid a nested switch case statement in the evaluate function.
     * Precisely, it determines the result value of the operation between two certain operands in the main expression,
       depending on the used operator

     * @param operator - operator in question
     * @param operand - second operand in the arithmetic expression
     * @param operands - a stack containing all operands in the main arithmetic expression until the point of function
                         call
     * @return - result of the given arithmetic operation
     */
    private static Double operandAccordingToOperator(String operator, double operand, Stack<Double> operands){
        switch(operator) {
            case "+":
                operand = operands.pop() + operand;
            case "-":
                operand = operands.pop() - operand;
            case "*":
                operand = operands.pop() * operand;
            case "/":
                operand = operands.pop() / operand;
            default:
                operand = Math.sqrt(operand);
        }
        return operand;
    }
}
