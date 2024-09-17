package ru.calculator.dto;

import org.mapstruct.Mapper;
import ru.calculator.model.SalaryInfo;

@Mapper
public interface SalaryInfoMapper {

    SalaryInfoDto toDto(SalaryInfo salaryInfo);

    SalaryInfo toEntity(SalaryInfoDto salaryInfoDto);

}
