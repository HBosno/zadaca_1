package ba.unsa.etf.rpr;

/**
 * Main class for parsing console input using args function parameter and validating said input, before passing it to
   ExpressionEvaluator function
 *
 */
public class App{

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

