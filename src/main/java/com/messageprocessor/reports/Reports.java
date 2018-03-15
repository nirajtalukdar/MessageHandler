package com.messageprocessor.reports;

public interface Reports<T> {

    void logReport(T saleDetails, boolean withAdjustments);
}
