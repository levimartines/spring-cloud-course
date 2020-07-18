package com.levimartines.limitsservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("service")
@Data
public class LimitConfig {

    private int min;
    private int max;

}
