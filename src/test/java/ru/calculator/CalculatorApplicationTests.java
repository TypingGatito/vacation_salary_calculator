package ru.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import ru.calculator.controller.CalculatorController;
import ru.calculator.dto.SalaryInfoMapper;
import ru.calculator.service.SalaryService;
import ru.calculator.service.third_party.IsDayOffService;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CalculatorApplicationTests {

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private CalculatorController calculatorController;

	@Autowired
	private SalaryInfoMapper salaryInfoMapper;

	@Autowired
	private IsDayOffService isDayOffService;

	@Autowired
	private SalaryService salaryService;

	@Test
	void contextLoads() {
		assertThat(webClientBuilder).isNotNull();
		assertThat(calculatorController).isNotNull();
		assertThat(salaryInfoMapper).isNotNull();
		assertThat(isDayOffService).isNotNull();
		assertThat(salaryService).isNotNull();
	}

}
