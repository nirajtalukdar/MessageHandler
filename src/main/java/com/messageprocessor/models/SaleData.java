package com.messageprocessor.models;

/**
 * This class holds the sale information from the message sent by the client.
 * It will be used to add to a list of all sale records which will give the
 * total count of messages received.
 */
public class SaleData {

    /** This field stores information of the product name */
    private String productType;

    /** This field holds the price of each product */
    private double productPrice;

    /** This field holds the actual selling price of the product. It is derived by multiplying the
     * product price with the total selling quantity */
    private double salePrice;

    /** This field contains the quantity of product sold */
    private int saleCount;


    public SaleData() {
        super();
    }

    public SaleData(String productType, double productPrice, int saleCount) {
        this.productType = productType;
        this.productPrice = productPrice;
        this.salePrice = productPrice * saleCount;
        this.saleCount = saleCount;
    }

    public String getProductType() {
        return productType;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getSaleCount() {
        return saleCount;
    }
}
