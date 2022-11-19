package ba.unsa.etf.rpr;

import java.util.Scanner;

import static java.lang.Character.isDigit;

//import static org.apache.maven.shared.utils.StringUtils.isNumeric;

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
        if(args.length==0){
            System.out.println("Unesite funkciju: ");
            String function;
            Scanner scanner = new Scanner(System.in);
            function = scanner.nextLine();
            System.out.println("Vrijednost unesenog aritmetičkog izraza: " + (new ExpressionEvaluator()).evaluate(function));
        }
        else {
            try {
                String s = args[0];
                if (!(s.charAt(0) == '(') || !(s.charAt(s.length()-1) == ')')) {
                    throw new RuntimeException("Izraz nije aritmetički validan!");
                }
                int leftParentheses = 0;
                int rightParentheses = 0;
                int numberOfOperators = 0;
                for (int i = 0; i < s.length(); i++) {  // replaced the ranged for loop as we will probably be in need to access next and previous String relative to current position at one point
                    if(i % 2 != 0 && i != s.length() - 1 && s.charAt(i) != ' '){ // whitespaces are positioned on odd index numbers, except for eventually the last position, which should always be ')'
                       throw new RuntimeException("Izraz nije aritmetički validan!");
                    }
                    if (!isDigit(s.charAt(i)) && s.charAt(i)!='(' && s.charAt(i)!=')' && !checkIfOperator(i, s)) {
                        throw new RuntimeException("Izraz nije aritmetički validan!");
                    }
                    if (s.charAt(i) == '(') {
                        leftParentheses = leftParentheses + 1;
                    } else if (s.charAt(i) == ')') {
                        rightParentheses = rightParentheses + 1;
                    } else if (checkIfOperator(i, s)) {
                        checkOperatorSurroundings(s, i);
                        numberOfOperators = numberOfOperators + 1;
                    }

                }

                // should we analyze the expressions we are working with, we will come to the conclusion that each left parenthesis corresponds to a single operator
                // obviously, we also need a matching number of right parentheses with respect to their left counterparts
                if (leftParentheses != numberOfOperators || leftParentheses != rightParentheses) {
                    throw new RuntimeException("Izraz nije aritmetički validan!");
                }
                ExpressionEvaluator evaluator = new ExpressionEvaluator();
                System.out.println("Vrijednost unesenog aritmetičkog izraza: " + evaluator.evaluate(s));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if char parameter is one of the operators we are working with
     * Used in main method for better code readability
     * @param i - index of the current char
     * @param s - string containing the whole arithmetic expression
     * @return - returns true if parameter char is an operator, otherwise return value is false
     */
    private static boolean checkIfOperator(int i, String s){
        if(s.charAt(i) == 's' && s.charAt(i+1) == 'q' && s.charAt(i+2) == 'r' && s.charAt(i+3) == 't'){
            return true;
        }
        return s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/';
    }

    /**
     * Important in expression validation. For operators +, -, * and / the following are valid constructions:
     1. ) operator (
     2. number operator number
     3. ) operator number
     4. number operator (
     * For sqrt operator, the following are valid constructions:
     1. ( sqrt (
     2. operator sqrt (, where operator is any operator excluding sqrt
     * Note that we can still have expressions such as a fourth root (number^(1/4) or sqrt(sqrt number)) however this is covered by case 1 as the valid expression would look as following:
       sqrt ( sqrt ( number ) )
     * @param s - string containing the whole arithmetic expression
     * @param i - index of the char we are currently testing
     * @throws RuntimeException if the expression is not valid
     */
    private static void checkOperatorSurroundings(String s, int i) throws RuntimeException{
        if(s.charAt(i) != 's'){
            if(!(s.charAt(i-2) == ')' && s.charAt(i+2) == '('  || isDigit(s.charAt(i-2)) && isDigit(s.charAt(i+2))|| s.charAt(i-2) == ')' && isDigit(s.charAt(i+2)) || isDigit(s.charAt(i-2)) && s.charAt(i+2) == '(')){
                throw new RuntimeException("Izraz nije aritmetički validan!");
            }
        }
        else{
            if(!(s.charAt(i-2) == '(' && s.charAt(i+2) == '(' || checkIfOperator(i-2, s) && s.charAt(i+2) == '(')){
                throw new RuntimeException("Izraz nije aritmetički validan!");
            }
        }
    }
}

