package ru.calculator.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.calculator.model.SalaryInfo;

@Mapper
public interface SalaryInfoMapper {

    SalaryInfoMapper INSTANCE = Mappers.getMapper(SalaryInfoMapper.class);

    SalaryInfoDto toDto(SalaryInfo salaryInfo);

    SalaryInfo toEntity(SalaryInfoDto salaryInfoDto);

}
