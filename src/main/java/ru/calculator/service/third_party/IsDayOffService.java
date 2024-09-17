package ru.calculator.service.third_party;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class IsDayOffService {

    @Value("${connections.is_day_off.url}")
    private String url;

    @Value("${connections.is_day_off.param1}")
    private String param1;

    @Value("${connections.is_day_off.param2}")
    private String param2;

    @Value("${connections.is_day_off.date_pattern}")
    private String datePattern;

    private final WebClient webClient;

    @Autowired
    public IsDayOffService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String interval(LocalDate dateStart, LocalDate dateEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        String formattedDateStart = dateStart.format(formatter);
        String formattedDateEnd = dateEnd.format(formatter);

        String urlN = url + "?" + param1 + "=" + formattedDateStart + "&" + param2 + "=" + formattedDateEnd;

        try {
            return webClient.get()
                    .uri(urlN)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            log.error("IsDayOffService.interval error", e);
            return null;
        }

    }

}
