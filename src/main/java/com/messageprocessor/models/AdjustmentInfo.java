package com.messageprocessor.models;

/** This class records all the adjustment related details */
public class AdjustmentInfo {

    /** Field to record the requested operation */
    private Operations operation;

    /**Field to record the requested adjustment amount */
    private double adjustmentAmount;

    /** Field to record the calculated adjusted amount */
    private double adjustedAmount;

    /** Field to record the total Post adjusted amount */
    private double totalPostAdjustedAmount;

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }

    public double getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(double adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public double getAdjustedAmount() {
        return adjustedAmount;
    }

    public void setAdjustedAmount(double adjustedAmount) {
        this.adjustedAmount = adjustedAmount;
    }

    public double getTotalPostAdjustedAmount() {
        return totalPostAdjustedAmount;
    }

    public void setTotalPostAdjustedAmount(double totalPostAdjustedAmount) {
        this.totalPostAdjustedAmount = totalPostAdjustedAmount;
    }
}
