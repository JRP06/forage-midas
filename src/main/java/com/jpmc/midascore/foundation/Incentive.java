package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//recieve data from an external API
@JsonIgnoreProperties(ignoreUnknown = true)
public class Incentive {
    //declare just one field – amount
    private float amount;

    //empty constructor- required by Jackson to deserialize API response into this object
    public Incentive(){
    }
    //parameterized constructor - for manually creating an Incentive object with a value
    public Incentive(float amount){
        this.amount = amount;
    }
    //getters and setters
    public float getAmount(){
        return amount;
    }

    public void setAmount(float amount){
        this.amount = amount;
    }
}
