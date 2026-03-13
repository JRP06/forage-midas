package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TransactionListener {

    private final DatabaseConduit databaseConduit;
    //data field for RestTemplate for injection
    private final RestTemplate restTemplate;

    //injection occurs here
    public TransactionListener(DatabaseConduit databaseConduit, RestTemplate restTemplate){
        this.databaseConduit = databaseConduit;
        this.restTemplate = restTemplate;
    }

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-core-group")
    public void handleTransaction(Transaction transaction){
        //find sender and recipient
        UserRecord sender = databaseConduit.findUserById(transaction.getSenderId());
        UserRecord recipient = databaseConduit.findUserById(transaction.getRecipientId());

        //validate
        if (sender == null || recipient == null) return;
        if (sender.getBalance() < transaction.getAmount()) return;

        //update balance
        sender.setBalance(sender.getBalance() - transaction.getAmount());
        recipient.setBalance(recipient.getBalance() + transaction.getAmount());

        //call incentive API
        Incentive incentive =  restTemplate.postForObject("http://localhost:8080/incentive", transaction, Incentive.class);
        //add incentive to recipient balance
        recipient.setBalance((recipient.getBalance() + incentive.getAmount()));

        //save everything
        databaseConduit.save(sender);
        databaseConduit.save(recipient);
        databaseConduit.save(new TransactionRecord(sender, recipient, transaction.getAmount(), incentive.getAmount()));

        if (sender.getName().equals("wilbur") || recipient.getName().equals("wilbur")) {
            System.out.println("wilbur balance: " + (sender.getName().equals("wilbur") ? sender.getBalance() : recipient.getBalance()));
        }
    }


}
