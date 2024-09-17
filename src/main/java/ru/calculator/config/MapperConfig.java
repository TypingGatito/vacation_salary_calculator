package ru.calculator.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.calculator.dto.SalaryInfoMapper;

@Configuration
public class MapperConfig {

    @Bean
    public SalaryInfoMapper salaryInfoMapper() {
        return Mappers.getMapper(SalaryInfoMapper.class);
    }

}