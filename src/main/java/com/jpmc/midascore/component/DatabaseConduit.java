package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    /*
    UserRepository and TransactionRepository are dependencis. and
    both get injected through the constructor
     */
    public DatabaseConduit(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    //this method saves userRecord Object to db.
    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    //save TransactionRecord object (just a transasction) into db
    public void save (TransactionRecord transactionRecord){
        transactionRepository.save(transactionRecord);
    }
    //find user by Id – returns null if user does not exist
    //this is used in TransactionListener to validate sender and recipient
    public UserRecord findUserById(long id){
        return userRepository.findById(id);
    }
}
