package spring.study.calculator.component;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DollarCalculatorTest {

    @MockBean
    private MarketApi marketApi; // Spring에서 Bean으로 등록하고 사용하고 있기 때문에 @MockBean 애노테이션 사용

    @Autowired
    private Calculator calculator;

    @Test
    void dollarCalculatorTest() {
        Mockito.when(marketApi.connect()).thenReturn(3000);

        int sum = calculator.sum(10, 10);
        int minus = calculator.minus(10, 10);

        assertEquals(60000, sum);
        assertEquals(0, minus);
    }
}