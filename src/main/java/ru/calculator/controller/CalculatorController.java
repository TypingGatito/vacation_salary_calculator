package ru.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.calculator.dto.SalaryInfoDto;
import ru.calculator.dto.SalaryInfoMapper;
import ru.calculator.service.SalaryService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CalculatorController {

    private final SalaryService salaryService;

    private SalaryInfoMapper salaryInfoMapper = SalaryInfoMapper.INSTANCE;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody SalaryInfoDto salaryInfo) {

        try {
            Double vacationSalary = salaryService.calculate(salaryInfoMapper.toEntity(salaryInfo));

            return ResponseEntity.ok(vacationSalary);

        } catch (Exception e) {
            log.error("CalculatorController.calculate error", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
