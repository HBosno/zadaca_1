package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for ExpressionEvaluator class.
 */

public class ExpressionEvaluatorTest {
    @Test
    public void invalidInputWhitespaceTest(){
        String s = " ( 2 + 3)";
        assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate(s);});
    }

    @Test
    public void missingParenthesesTest(){
        String s = "( 2 + 3 - 3)";
        assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate(s);});
    }

    @Test
    public void invalidInputTest(){
        String s = "( 5 + + )";
        assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate(s);});
    }

    @Test
    public void correctValueTest(){
        String s = "( 2 + ( 8 / 4 ) )";
        assertEquals(new ExpressionEvaluator().evaluate(s), 4);
    }

    @Test
    public void correctValueSqrtTest(){
        String s = "( sqrt ( 36 ) + sqrt ( 49 ) )";
        assertEquals(new ExpressionEvaluator().evaluate(s), 13);
    }


}
