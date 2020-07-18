package com.levimartines.currencyexchangeservice;

import brave.sampler.Sampler;
import com.levimartines.currencyexchangeservice.model.ExchangeValue;
import com.levimartines.currencyexchangeservice.repositories.ExchangeValueRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@EnableDiscoveryClient
@Slf4j
public class CurrencyExchangeServiceApplication implements CommandLineRunner {

    private final ExchangeValueRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ExchangeValue exc1 = new ExchangeValue("USD", "INR", BigDecimal.valueOf(75.2));
        ExchangeValue exc2 = new ExchangeValue("INR", "USD", BigDecimal.valueOf(0.013));
        ExchangeValue exc3 = new ExchangeValue("USD", "BRL", BigDecimal.valueOf(5.36));
        ExchangeValue exc4 = new ExchangeValue("BRL", "USD", BigDecimal.valueOf(0.19));
        repository.saveAll(Arrays.asList(exc1, exc2, exc3, exc4));
        log.info("## CURRENCY EXCHANGE SERVICE STARTED");
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
