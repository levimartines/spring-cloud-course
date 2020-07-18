package com.levimartines.currencyconversionservice.controller;

import com.levimartines.currencyconversionservice.model.CurrencyConversionBean;
import com.levimartines.currencyconversionservice.service.CurrencyConversionService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CurrencyConversionController {

    private final CurrencyConversionService service;

    @GetMapping("/currency-converter")
    public ResponseEntity<CurrencyConversionBean> convertyCurrency(@RequestParam String from,
        @RequestParam String to, @RequestParam String quantity) {
        log.info("CONVERTING [{}] to [{}]", from, to);
        return ResponseEntity.ok(service.convertCurrencyFeign(from, to, new BigDecimal(quantity)));
    }

    @GetMapping("/currency-converter-feign")
    public ResponseEntity<CurrencyConversionBean> convertyCurrencyFane(@RequestParam String from,
        @RequestParam String to, @RequestParam String quantity) {
        return ResponseEntity.ok(service.convertCurrencyFeign(from, to, new BigDecimal(quantity)));
    }
}
