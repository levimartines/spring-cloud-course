package com.levimartines.limitsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitConfiguration {

    private int min;
    private int max;
}
