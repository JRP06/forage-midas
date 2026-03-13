package com.jpmc.midascore.Controller;

import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class BalanceController {
    private DatabaseConduit databaseConduit;

    //constructor - inject DatabaseConduit
    public BalanceController (DatabaseConduit databaseConduit){
        this.databaseConduit = databaseConduit;
    }
    //handle Get/balanc user id = 1
    @GetMapping("/balance")
    public Balance getBalance(@RequestParam long userId){
        //find by user id
        UserRecord user = databaseConduit.findUserById(userId);

        //if user does not exist return balance of 0
        if (user == null)
            return new Balance(0);

        //if user exists return their balance
        return new Balance(user.getBalance());
    }
}
