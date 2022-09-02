package spring.study.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class MarketApi {

    public int connect() {
        // naver || kakao Open API 사용하여 환율 계산
        return 1100;
    }
}
