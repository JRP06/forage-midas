package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {
    /*
    what this class does?
    - Watch the trader-updates topic
    - When a message arrives, wake up
    - Print it out so your debugger can see it
     */
    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-core-group")
    public void handleTransaction(Transaction transaction){
        //transaction has arrrived here
        System.out.println(transaction);
    }

}
