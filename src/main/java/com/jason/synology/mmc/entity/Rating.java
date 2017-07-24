package com.jason.synology.mmc.entity;

import java.math.BigDecimal;

/**
 * Copyright with Industics company.
 * Author: jason.xu
 * Date: 2017/7/24
 * Time: 下午3:08
 */
public class Rating {
    private int start;
    private BigDecimal max;
    private BigDecimal average;
    private BigDecimal min;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }
}
