package com.messageprocessor.client;

import com.messageprocessor.models.Message;
import com.messageprocessor.models.Operations;
import com.messageprocessor.service.MessageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageSender {

    /** This method adds random messages to a list which is then sent to the message
     * processor for further execution */
    public List<Message> generateSaleMessages() {

        Random rNum = new Random();
        List<Message> messageList = new ArrayList<>();
        int i = 0;

        while (i<50) {
            Message msg = new Message();
            switch (rNum.nextInt(6)) {

                case 0:
                    msg.setProductType("Apple");
                    msg.setProductValue(20);
                    msg.setSaleCount(1);
                    msg.setAdjustmentOperation(Operations.NONE);
                    msg.setAdjustmentAmount(0);
                    break;
                case 1:
                    msg.setProductType("Mango");
                    msg.setProductValue(10);
                    msg.setSaleCount(1);
                    msg.setAdjustmentOperation(Operations.NONE);
                    msg.setAdjustmentAmount(0);
                    break;
                case 2:
                    msg.setProductType("Orange");
                    msg.setProductValue(5);
                    msg.setSaleCount(2);
                    msg.setAdjustmentOperation(Operations.NONE);
                    msg.setAdjustmentAmount(0);
                    break;
                case 3:
                    msg.setProductType("Apple");
                    msg.setProductValue(20);
                    msg.setSaleCount(1);
                    msg.setAdjustmentOperation(Operations.ADD);
                    msg.setAdjustmentAmount(10);
                    break;
                case 4:
                    msg.setProductType("Mango");
                    msg.setProductValue(10);
                    msg.setSaleCount(1);
                    msg.setAdjustmentOperation(Operations.SUBTRACT);
                    msg.setAdjustmentAmount(2);
                    break;
                case 5:
                    msg.setProductType("Orange");
                    msg.setProductValue(10);
                    msg.setSaleCount(1);
                    msg.setAdjustmentOperation(Operations.MULTIPLY);
                    msg.setAdjustmentAmount(2);
                    break;
            }
            messageList.add(msg);
            i++;
        }

        return messageList;


    }

    public static void main(String[] args) {

        MessageSender messageSender = new MessageSender();
        MessageHandler messageHandler = new MessageHandler();

        messageSender.generateSaleMessages().forEach(messageHandler::processSalesMessage);



    }
}
