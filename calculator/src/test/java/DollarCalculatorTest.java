import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class) // Mockito를 사용하기 위해 확장 명시
class DollarCalculatorTest {

    @Mock
    private MarketApi marketApi;

    @BeforeEach // Test가 실행되기 이전에
    public void init() {
        Mockito.lenient()
            .when(marketApi.connect()) // connect() 메서드가 호출되면
            .thenReturn(3000); // 3000 값을 할당하도록 지정
    }

    @Test
    void testHello() {
        System.out.println("hello");
    }

    @Test
    void dollarTest() {

        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        assertEquals(22000, calculator.sum(10, 10));
        assertEquals(0, calculator.minus(10, 10));
    }

    @Test
    void mockTest() {

        DollarCalculator dollarCalculator = new DollarCalculator(marketApi); // Mock 데이터가 할당된 MarketApi 사용
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        assertEquals(60000, calculator.sum(10, 10));
        assertEquals(0, calculator.minus(10, 10));
    }
}