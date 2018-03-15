package com.messageprocessor.models;

public class Message {

    private String productType;
    private double productValue;
    private int saleCount;
    private Operations adjustmentOperation;
    private double adjustmentAmount;


    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getProductValue() {
        return productValue;
    }

    public void setProductValue(double productValue) {
        this.productValue = productValue;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public Operations getAdjustmentOperation() {
        return adjustmentOperation;
    }

    public void setAdjustmentOperation(Operations adjustmentOperation) {
        this.adjustmentOperation = adjustmentOperation;
    }

    public double getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(double adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }
}
