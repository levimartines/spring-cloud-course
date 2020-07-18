package com.levimartines.currencyconversionservice.proxy;

import com.levimartines.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Create a Interface to dont use RestTemplate with Feign
// @FeignClient(name = "currency-exchange-service")
@FeignClient(name = "zuul-api-gateway-service")
// Client side Load balancing with Ribbon
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    // @GetMapping("currency-exchange")
    @GetMapping("currency-exchange-service/currency-exchange")
    CurrencyConversionBean retrieveExchangeValue(@RequestParam("from") String from,
        @RequestParam("to") String to);

}
