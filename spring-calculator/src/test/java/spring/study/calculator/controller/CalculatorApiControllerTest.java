package spring.study.calculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import spring.study.calculator.component.Calculator;
import spring.study.calculator.component.DollarCalculator;
import spring.study.calculator.component.MarketApi;
import spring.study.calculator.dto.Req;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CalculatorApiController.class)
@AutoConfigureWebMvc
@Import({ Calculator.class, DollarCalculator.class }) // Controller에서 주입받고 있는 Calculator와 Calculator가 주입받고 있는 ICalculator의 구현체인 DollarCalculator를 Import 처리
class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi; // DollarCalculator가 주입받고 있는 MarketApi는 MockBean으로 처리

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        Mockito.when(marketApi.connect()).thenReturn(3000); // connect() 메서드가 작동될 경우 초기화 처리
    }

    @Test
    void sumTest() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                .queryParam("x", "10")
                .queryParam("y", "10")
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        ).andExpect(
            MockMvcResultMatchers.content().string("60000")
        ).andDo(
            MockMvcResultHandlers.print()
        );
    }

    @Test
    void minusTest() throws Exception {

        Req request = new Req(10, 10);

        mockMvc.perform(
            MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.response.resultCode").value(HttpStatus.OK.getReasonPhrase())
        ).andDo(
            MockMvcResultHandlers.print()
        );
    }
}