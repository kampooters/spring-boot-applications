package com.org.batch.algorithm;

import java.util.List;

public interface  ICalculator {
    static double getReward(List<RewardCondition>  rewardConditions, double amountValue){
        double reward = 0;
        double amount = amountValue;
        if(amountValue < 0){
            amount = Math.abs(amountValue);
        }
        for (int i = 0; i < rewardConditions.size(); i++) {
            RewardCondition rewardCondition = rewardConditions.get(i);
            if(amount >= rewardCondition.getStartAmount() && amount <= rewardCondition.getEndAmount() && !rewardCondition.getCondition().isEmpty()){
                reward = (rewardCondition.getReward()/100)*amount;
//                return percentage;
            }else if(amount >= rewardCondition.getStartAmount() && amount <= rewardCondition.getEndAmount()){
                reward =  rewardCondition.getReward();
            }else if(amount >= rewardCondition.getStartAmount() && 0 == rewardCondition.getEndAmount()){
                reward = (rewardCondition.getReward()/100)*amount;
//                return percentage;
            }
        }
        if(amountValue < 0)
            reward = reward-(reward*2);
        reward = Math.round(reward);
        return reward;
    }
}
