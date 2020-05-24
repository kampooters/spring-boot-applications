package com.org.batch.algorithm;

public class RewardCondition {
    double startAmount = 0.0;
    double endAmount = 0.0;
    double reward = 0.0;
    String condition = "";
    String transactionType = "";


    public RewardCondition(String transactionType, double startAmount, double endAmount, double reward, String condition) {
        this.transactionType = transactionType;
        this.startAmount = startAmount;
        this.endAmount = endAmount;
        this.reward = reward;
        this.condition = condition;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public RewardCondition(){}

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    public double getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(double endAmount) {
        this.endAmount = endAmount;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
