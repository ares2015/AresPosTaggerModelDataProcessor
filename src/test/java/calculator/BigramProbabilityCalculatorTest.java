package calculator;

import com.trainingdataprocessor.calculator.BigramProbabilityCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 11/20/2016.
 */
public class BigramProbabilityCalculatorTest {

    @Test
    public void test(){
        System.out.println(BigramProbabilityCalculator.calculate(1, 2));
        assertEquals(50.0, BigramProbabilityCalculator.calculate(1, 2), 0);

        System.out.println(BigramProbabilityCalculator.calculate(1, 1));
        assertEquals(100.0, BigramProbabilityCalculator.calculate(1, 1), 0);
    }
}