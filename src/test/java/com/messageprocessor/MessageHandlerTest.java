package com.messageprocessor;

import com.messageprocessor.models.Message;
import com.messageprocessor.models.Operations;
import com.messageprocessor.service.MessageHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageHandlerTest {

    MessageHandler msgHandler = new MessageHandler();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testWhenValidMessageThenSaleRecorded() {

        Message msg = new Message();
        msg.setProductType("Apple");
        msg.setSaleCount(2);
        msg.setProductValue(3);
        msg.setAdjustmentOperation(Operations.NONE);
        msgHandler.processSalesMessage(msg);

        Assertions.assertEquals("Apple", msgHandler.getSalesList().get(0).getProductType());
        Assertions.assertEquals(2, msgHandler.getSalesList().get(0).getSaleCount());
    }

    @Test
    public void testWhenInValidMessageThenSaleNotRecorded() {

        Message msg = new Message();
        msg.setProductType("Apple");
        msg.setSaleCount(2);
        msg.setProductValue(0);
        msg.setAdjustmentOperation(Operations.NONE);
        msgHandler.processSalesMessage(msg);

        Assertions.assertEquals(0, msgHandler.getSalesList().size());
    }

}