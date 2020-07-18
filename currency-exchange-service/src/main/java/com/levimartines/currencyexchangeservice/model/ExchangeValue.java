package com.levimartines.currencyexchangeservice.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "EXCHANGE_VALUE")
public class ExchangeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXC_ID")
    private Long id;

    @Column(name = "CUR_FROM")
    private String from;

    @Column(name = "CUR_TO")
    private String to;

    @Column(name = "EXC_MULTIPLE")
    private BigDecimal conversionMultiple;
    private int port;

    public ExchangeValue(String from, String to, BigDecimal conversionMultiple) {
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }
}
