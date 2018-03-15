package com.messageprocessor.models;

import java.util.ArrayList;
import java.util.List;

/** This class will be used to store all the sale records against the respective
 * product. It will be stored in a HashMap to maintain a product -> sale relation */
public class SaleRecordDetails {

    private String productType;
    private int saleCount;
    private double totalSaleValue;
    private List<AdjustmentInfo> adjustmentList = new ArrayList<>();

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public double getTotalSaleValue() {
        return totalSaleValue;
    }

    public void setTotalSaleValue(double totalSaleValue) {
        this.totalSaleValue = totalSaleValue;
    }

    public List<AdjustmentInfo> getAdjustmentList() {
        return adjustmentList;
    }

    public void setAdjustmentList(List<AdjustmentInfo> adjustmentList) {
        this.adjustmentList = adjustmentList;
    }
}
