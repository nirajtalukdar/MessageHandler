package com.messageprocessor.reports;

import com.messageprocessor.models.SaleRecordDetails;

import java.util.Map;

/** This class is responsible for generating the reports for the sales
 * that have been recorded after specific intervals */
public class SalesReport implements Reports<Map<String, SaleRecordDetails>>{

    @Override
    public void logReport(Map<String, SaleRecordDetails> saleDetails, boolean withAdjustments) {

        if (withAdjustments) {
            logSaleReportWithAdjustments(saleDetails);

        } else {
            logSaleReportWithoutAdjustments(saleDetails);
        }
    }

    /** This method records the product, quantity sold and total price after every 10 messages received
     * from the client */
    public static void logSaleReportWithoutAdjustments(Map<String, SaleRecordDetails> saleDetails) {
        System.out.println("********** Sale Report *********\n");
        System.out.println("Product_Type\t"+"|"+"\tQuantity_Sold\t"+"|"+"\tTotalValue");

        saleDetails.forEach((k,v) -> System.out.println(v.getProductType()+
                                     String.format("%20s",v.getSaleCount())+
                                     String.format("%20s",v.getTotalSaleValue())));

    }

    /** This method records the product, operation, adjustment amount requested, adjusted amount and total
     * adjustment amount after every 50 messages received from the client */
    public static void logSaleReportWithAdjustments(Map<String, SaleRecordDetails> saleDetails) {
        System.out.println("\n\nMessageProcessing Application will pause now and stop accepting new messages....");
        System.out.println("\n********** Sale Adjustment Report *********\n");
        System.out.println("Product_Type\t"+"|"+"\tAdjustmentOperation\t"+"|"+"\tAdjustmentAmount\t"+"|"+"\tAdjustedAmount\t"+"|"+"\tTotalAmountPostAdjustment");

        saleDetails.forEach((k,v) -> v.getAdjustmentList().forEach(a -> System.out.println(v.getProductType()+
                                                                        String.format("%22s", a.getOperation())+
                                                                        String.format("%22s", a.getAdjustmentAmount())+
                                                                        String.format("%22s", a.getAdjustedAmount())+
                                                                        String.format("%22s", a.getTotalPostAdjustedAmount()))));
    }
}
