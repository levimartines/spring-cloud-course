package com.levimartines.currencyconversionservice.service;

import com.levimartines.currencyconversionservice.model.CurrencyConversionBean;
import com.levimartines.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CurrencyConversionService {

    private final CurrencyExchangeServiceProxy proxy;

    @Deprecated
    public CurrencyConversionBean convertCurrency(String from, String to, BigDecimal quantity) {
        String url = String
            .format("http://localhost:8000/currency-exchange?from=%s&to=%s", from, to);

        ResponseEntity<CurrencyConversionBean> response = new RestTemplate()
            .getForEntity(url, CurrencyConversionBean.class);
        if (response.getBody() != null && response.getBody().getConversionMultiple() != null) {
            CurrencyConversionBean bean = response.getBody();
            bean.setQuantity(quantity);
            BigDecimal totalAmount = quantity.multiply(bean.getConversionMultiple());
            bean.setTotalAmount(totalAmount);
            return bean;
        }
        return null;
    }

    public CurrencyConversionBean convertCurrencyFeign(String from, String to, BigDecimal quantity) {
        CurrencyConversionBean bean = proxy.retrieveExchangeValue(from, to);
        if (bean.getConversionMultiple() != null) {
            bean.setQuantity(quantity);
            BigDecimal totalAmount = quantity.multiply(bean.getConversionMultiple());
            bean.setTotalAmount(totalAmount);
            if(totalAmount.compareTo(BigDecimal.valueOf(1000L)) > 0) {
                log.warn("SO MUCH MONEY!!! - [{}]", totalAmount);
            }
            return bean;
        }
        return null;
    }


}
