package ru.calculator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.calculator.model.SalaryInfo;
import ru.calculator.service.third_party.IsDayOffService;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryService {

    @Value("${salary.days_in_month}")
    private Double daysInMonth;

    private final IsDayOffService isDayOffService;

    public Double calculate(SalaryInfo salaryInfo) {
        Double vacationSalary;
        if (salaryInfo.getVacationStart() == null) {
            vacationSalary = calculate(salaryInfo.getSalary(), salaryInfo.getDaysOfVacation());
        } else {
            vacationSalary = calculateWithStart(salaryInfo.getSalary(),
                    salaryInfo.getVacationStart(),
                    salaryInfo.dateEnd());
        }

        return vacationSalary;
    }

    private Double calculate(Double averageSalary, Integer days) {
        return averageSalary * days / daysInMonth;
    }

    private Double calculateWithStart(Double averageSalary, LocalDate dateStart, LocalDate dateEnd) {
        String weekends = isDayOffService.interval(dateStart, dateEnd);

        long workingDays;

        if (weekends == null) {
            log.info("weekends is null");
            workingDays = 0L;
        } else {
            workingDays = weekends.chars()
                    .filter((int c) -> c == '0')
                    .count();
        }

        return workingDays * averageSalary / daysInMonth;
    }

}
