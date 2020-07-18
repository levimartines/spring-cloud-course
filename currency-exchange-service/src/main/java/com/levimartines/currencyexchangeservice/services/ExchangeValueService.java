package com.levimartines.currencyexchangeservice.services;

import com.levimartines.currencyexchangeservice.model.ExchangeValue;
import com.levimartines.currencyexchangeservice.repositories.ExchangeValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ExchangeValueService {

    private final ExchangeValueRepository repository;

    public ExchangeValue findByFromAndTo(String from, String to) {
        return repository.findByFromAndTo(from, to);
    }

}
