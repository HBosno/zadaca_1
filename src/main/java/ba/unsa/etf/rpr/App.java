package ba.unsa.etf.rpr;

import static org.apache.maven.shared.utils.StringUtils.isNumeric;

/**
 * Main class for parsing console input using args function parameter and validating said input, before passing it to
   ExpressionEvaluator function
 *
 */
public class App{
    /**
     * Main method that will handle console input, construct a string to pass to Dijkstra's algorithm and return the
       value of the arithmetic expression contained in the string
     * @param args - Input from console, array of strings to be parsed, validated and merged into a single string
     *               for ExpressionEvaluator to work with next
     * @throws RuntimeException - Will throw an exception in case of an invalid arithmetic expression
     */
    public static void main( String[] args ) throws RuntimeException {
        try {
            StringBuilder builder = new StringBuilder();
            if (!args[0].equals("(") || !args[args.length - 1].equals(")")) {
                throw new RuntimeException("Izraz nije aritmetički validan!");
            }
            int leftParentheses = 0;
            int rightParentheses = 0;
            int numberOfOperators = 0;
            for (int i = 0; i < args.length; i++) {  // replaced the ranged for loop as we will probably be in need to access next and previous String relative to current position at one point
                if(!isNumeric(args[i]) && !args[i].equals("(") && !args[i].equals(")") && !checkIfOperator(args[i])){
                    throw new RuntimeException("Izraz nije aritmetički validan!");
                }
                if(args[i].equals("(")){
                    leftParentheses = leftParentheses + 1;
                }
                else if(args[i].equals(")")){
                    rightParentheses = rightParentheses + 1;
                }
                else if(checkIfOperator(args[i])){
                    numberOfOperators = numberOfOperators + 1;
                }
                builder.append(args[i]);
                if(i != args.length - 1){
                    builder.append(" ");
                }
            }

            // should we analyze the expressions we are working with, we will come to the conclusion that each left parenthesis corresponds to a single operator
            // obviously, we also need a matching number of right parentheses with respect to their left counterparts
            if(leftParentheses != numberOfOperators || leftParentheses != rightParentheses){
                throw new RuntimeException("Izraz nije aritmetički validan!");
            }
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            System.out.println("Vrijednost unesenog aritmetičkog izraza: " + evaluator.evaluate(builder.toString()));
        }
        catch(RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if parameter String is one of the operators we are working with
     * Used in main method for better code readability
     * @param s - String to be tested
     * @return - returns true if parameter string is an operator, otherwise return value is false
     */
    private static boolean checkIfOperator(String s){
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("sqrt");
    }
}

