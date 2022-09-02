package spring.study.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Res {

    private int result;
    private Body response;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {

        private String resultCode = HttpStatus.OK.getReasonPhrase();
    }
}
