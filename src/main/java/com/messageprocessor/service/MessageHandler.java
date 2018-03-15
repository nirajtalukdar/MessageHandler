package com.messageprocessor.service;

import com.messageprocessor.exceptions.InvalidMessageException;
import com.messageprocessor.models.AdjustmentInfo;
import com.messageprocessor.models.Message;
import com.messageprocessor.models.SaleData;
import com.messageprocessor.models.SaleRecordDetails;
import com.messageprocessor.reports.Reports;
import com.messageprocessor.reports.SalesReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class is responsible for processing the messages received from the client */
public class MessageHandler {

    private List<SaleData> salesList = new ArrayList<>();
    private Map<String, SaleRecordDetails> salesMap = new HashMap<>();

    public void processSalesMessage(Message msg) {

        try {
            if (isMessageValidated(msg)) {
                SaleRecordDetails saleDetails = new SaleRecordDetails();
                SaleData sRecord = new SaleData(msg.getProductType(), msg.getProductValue(), msg.getSaleCount());
                salesList.add(sRecord);

                if (salesMap.containsKey(sRecord.getProductType())) {
                    saleDetails = updateSaleRecordDetails(msg, sRecord, salesMap.get(sRecord.getProductType()), false);

                } else {

                    saleDetails = updateSaleRecordDetails(msg, sRecord, saleDetails, true);
                }

                salesMap.put(msg.getProductType(), saleDetails);

                if (salesList.size() %10 == 0) {
                    Reports<Map<String, SaleRecordDetails>> logger = new SalesReport();
                    logger.logReport(salesMap, false);

                }
                if (salesList.size() %50 == 0) {
                    Reports<Map<String, SaleRecordDetails>> logger = new SalesReport();
                    logger.logReport(salesMap, true);
                }

            }
        } catch (InvalidMessageException e) {
            //log the error message with valid error codes
        }
    }

    private SaleRecordDetails updateSaleRecordDetails(Message msg, final SaleData sRecord, SaleRecordDetails saleDetails, boolean isNewProduct) {

        if (isNewProduct) {

            saleDetails.setProductType(sRecord.getProductType());
            saleDetails.setSaleCount(sRecord.getSaleCount());
            saleDetails.setTotalSaleValue(sRecord.getSalePrice());
            saleDetails.setAdjustmentList(new ArrayList<>());

        } else {

            double adjustedAmount = 0.0;
            double totalSaleAdjustedAmount = 0.0;

            switch (msg.getAdjustmentOperation()) {

                case ADD:
                    adjustedAmount = salesList.stream()
                            .filter(s -> s.getProductType()
                                    .equalsIgnoreCase(sRecord.getProductType())).count() * msg.getAdjustmentAmount();
                    totalSaleAdjustedAmount = saleDetails.getTotalSaleValue() + adjustedAmount;
                    break;

                case SUBTRACT:
                    adjustedAmount = salesList.stream()
                            .filter(s -> s.getProductType()
                                    .equalsIgnoreCase(sRecord.getProductType())).count() * msg.getAdjustmentAmount();
                    totalSaleAdjustedAmount = saleDetails.getTotalSaleValue() - adjustedAmount;
                    break;

                case MULTIPLY:
                    for (SaleData sData : salesList) {
                        if (sData.getProductType().equalsIgnoreCase(sRecord.getProductType())) {
                            adjustedAmount = adjustedAmount + (sRecord.getSalePrice() * msg.getAdjustmentAmount());
                        }
                    }
                    totalSaleAdjustedAmount = saleDetails.getTotalSaleValue() + adjustedAmount;
                    break;

                case NONE:
                    // message with no adjustment.
                    break;
            }
            AdjustmentInfo aInfo = new AdjustmentInfo();
            aInfo.setOperation(msg.getAdjustmentOperation());
            aInfo.setAdjustmentAmount(msg.getAdjustmentAmount());
            aInfo.setAdjustedAmount(adjustedAmount);
            aInfo.setTotalPostAdjustedAmount(totalSaleAdjustedAmount);
            saleDetails.getAdjustmentList().add(aInfo);
            saleDetails.setSaleCount(saleDetails.getSaleCount() + sRecord.getSaleCount());
            saleDetails.setTotalSaleValue(saleDetails.getTotalSaleValue() + sRecord.getSalePrice());

        }

        return saleDetails;
    }

    /** Method to validate the message sent by the client
     * @return boolean
     * @throws InvalidMessageException */
    private boolean isMessageValidated(Message msg) throws InvalidMessageException {

        if (msg.getProductValue() > 0) {

            if (!msg.getAdjustmentOperation().name().equalsIgnoreCase("none")) {
                if (msg.getAdjustmentAmount() < 1) {
                    throw new InvalidMessageException("Error: Message cannot have negative adjustment value for operation : "+msg.getAdjustmentOperation().name());
                }
            }
            if (msg.getSaleCount() < 1) {
                throw new InvalidMessageException("Error: Message contains invalid sale count");
            }

        } else {
            throw new InvalidMessageException("Error: Sale cannot be of negative or zero value");
        }
        return true;
    }

    public List<SaleData> getSalesList() {
        return salesList;
    }

}
