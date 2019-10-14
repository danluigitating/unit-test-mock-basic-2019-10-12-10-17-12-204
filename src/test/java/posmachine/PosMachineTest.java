package posmachine;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class PosMachineTest {


    @Test
    public void should_get_receipt_using_real_price_calculator() {
        //given
        PriceCalculator calculator = new PriceCalculator();
        PosMachine posMachine = new PosMachine(calculator);
        //when

        //then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> posMachine.getReceipt("Coke"));
    }

    @Test
    public void should_get_receipt_using_sub_price_calculator() {
        //given
        PriceCalculator calculator= new StubPriceCalculator();
        PosMachine posMachine= new PosMachine(calculator);

        //when
        //then
        Assertions.assertEquals(posMachine.getReceipt("Coke"), "Name: Coke, Price: 10.0");
    }

    @Test
    public void should_get_receipt_using_real_price_calculator_with_mockito() {
        //given
        PriceCalculator calculator= Mockito.mock(PriceCalculator.class);
        PosMachine posMachine= new PosMachine(calculator);

        //when
        when(calculator.calculate("Coke")).thenReturn(10.0);
        //then
        Assertions.assertEquals(posMachine.getReceipt("Coke"), "Name: Coke, Price: 10.0");
    }

    private class StubPriceCalculator extends PriceCalculator {
        @Override
        public double calculate(String productName) {
            return 10.0;
        }
    }
}
