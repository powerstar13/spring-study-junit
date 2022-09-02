package spring.study.calculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calculator {

    private final ICalculator iCalculator;

    public int sum(int x, int y) {
        iCalculator.init();
        return iCalculator.sum(x, y);
    }

    public int minus(int x, int y) {
        iCalculator.init();
        return iCalculator.minus(x, y);
    }
}
