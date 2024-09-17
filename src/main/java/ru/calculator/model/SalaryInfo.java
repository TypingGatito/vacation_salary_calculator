package ru.calculator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SalaryInfo {

    private Double salary;

    private Integer lengthOfVacation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate vacationStart;

    public LocalDate dateEnd() {
        return getVacationStart().plusDays(getLengthOfVacation() - 1);
    }

}
