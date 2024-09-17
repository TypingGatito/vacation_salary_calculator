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

    private final SalaryInfoMapper salaryInfoMapper;

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody SalaryInfoDto salaryInfo) {

        if (salaryInfo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No body");
        }

        if (salaryInfo.getSalary() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must send salary {salary}");
        }

        if (salaryInfo.getLengthOfVacation() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must write length of vacation {lengthOfVacation}");
        }

        try {
            Double vacationSalary = salaryService.calculate(salaryInfoMapper.toEntity(salaryInfo));

            return ResponseEntity.ok(vacationSalary.toString());

        } catch (Exception e) {
            log.error("CalculatorController.calculate error", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
