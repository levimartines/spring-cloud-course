package com.levimartines.limitsservice.controller;

import com.levimartines.limitsservice.config.LimitConfig;
import com.levimartines.limitsservice.model.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class LimitsConfigurationController {

    private final LimitConfig limitConfig;

    @GetMapping("limits")
    @HystrixCommand(fallbackMethod = "fallbackGetLimitsConfig")
    public ResponseEntity<LimitConfiguration> getLimitsFromConfig() {
        return ResponseEntity
            .ok(new LimitConfiguration(limitConfig.getMin(), limitConfig.getMax()));
    }

    @GetMapping("limits-error")
    @HystrixCommand(fallbackMethod = "fallbackGetLimitsConfig")
    public ResponseEntity<LimitConfiguration> getLimitsException() {
        throw new RuntimeException("Erro ao recuperar config");
    }

    public ResponseEntity<LimitConfiguration> fallbackGetLimitsConfig(){
        return ResponseEntity
            .ok(new LimitConfiguration(0, 99999));
    }

}
