package com.levimartines.currencyconversionservice.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CurrencyConversionBean {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalAmount;
    private int port;

}
