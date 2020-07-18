package com.levimartines.currencyexchangeservice.controller;

import com.levimartines.currencyexchangeservice.model.ExchangeValue;
import com.levimartines.currencyexchangeservice.services.ExchangeValueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CurrencyExchangeController {

    private final Environment env;
    private final ExchangeValueService service;

    @GetMapping("currency-exchange")
    public ResponseEntity<ExchangeValue> retrieveExchangeValue(@RequestParam String from,
        @RequestParam String to) {
        ExchangeValue value = service.findByFromAndTo(from, to);
        String port = env.getProperty("local.server.port");
        value.setPort(port != null ? Integer.parseInt(port) : 0);
        log.info("CURRENCY EXCHANGE - [{}]", value);
        return ResponseEntity.ok(value);
    }

}
