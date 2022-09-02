package spring.study.calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.study.calculator.component.Calculator;
import spring.study.calculator.dto.Req;
import spring.study.calculator.dto.Res;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorApiController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y) {
        return calculator.sum(x, y);
    }

    @PostMapping("/minus")
    public Res minus(@RequestBody Req request) {

        int result = calculator.minus(request.getX(), request.getY());

        Res response = new Res();
        response.setResult(result);
        response.setResponse(new Res.Body());

        return response;
    }
}
