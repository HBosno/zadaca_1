package ba.unsa.etf.rpr;

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
            for (String s : args) {

                builder.append(s).append(" ");
            }
        }
        catch(RuntimeException e) {
            e.printStackTrace();
        }
    }
}

