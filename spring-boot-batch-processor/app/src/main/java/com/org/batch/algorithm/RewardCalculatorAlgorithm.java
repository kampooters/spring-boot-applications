package com.org.batch.algorithm;

import com.org.batch.Customer;
import com.org.persistence.model.CustomerReward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author abdul.rehman4
 * Calculates the Rewards for Customers. Is completely dynamic algo where you can define the algo conditions in  .properties file and the algo will work.
 * Here are details how it works.
 *
 *      * batch.algorithm.rules.transaction.types: ecom, remaining
 *      * batch.algorithm.rules.transaction.ecom: 0-2.49*0, 2.50-4.99*1, 5.00-9.99*2, 10.00-99.99*3, 100.00-299.99*7*%, 300.00-999.99*10*%, 1000-0*15*%
 *      * batch.algorithm.rules.transaction.remaining: 0-2.49*1, 2.50-4.99*2, 5.00-9.99*3, 10.00-99.99*4, 100.00-299.99*9*%, 300.00-999.99*11*%, 1000-0*13*%
 *
 *      * batch.algorithm.rules.transaction.types: define the transaction types with comma separated
 *
 *      once you define the transaction types then you can define the reward rules for above transaction uniqly by adding the following line like
 *      * batch.algorithm.rules.transaction. <transaction type like ecome>: < rule like start_amount(double) - max_amount(double)*reward(double)>
 *                                                                        : 0-2.49*0 (start_amount(double) - max_amount(double)*reward(double))
 *                                                                        : 300.00-999.99*11*% (start_amount(double) - max_amount(double)*reward amount(double)*Mathematical condition like percentage (String))
 *                                                                        : Note: you can add unlimited conditions with comma separation
 *
 *
 *     note: If you dont define the * batch.algorithm.rules.transaction. <transaction type like ecome> then their sould be one with
 *     * batch.algorithm.rules.transaction. <transaction type like ecome>
 *         which will be calculated for other transaction types
 */
@Service
public class RewardCalculatorAlgorithm implements IAlgorithm, ICalculator {

    @Autowired
    private Environment env;

    @Value(value = "${batch.algorithm.rules.transaction.types}")
    public String[] transactionTypes;

    @Value(value = "${batch.algorithm.rules.transaction.marchant.factor}")
    public String [] marchantFactorList;

    private Map<String, List<RewardCondition>> rewardConditionListMap = null;
    private Map<String, CustomerReward> customerRewardMap = new Hashtable<>();
    private Map<String, String> metaData = null;

    /**
     * Sets metadata as we store @{@link Hashtable}  having clientName as String->String
     * @param metaDataObj
     */
    @Override
    public void setMetaData(Object metaDataObj){
        this.metaData = (Hashtable)metaDataObj;
    }


    @Override
    public Object calculate(Object objectList) throws AlgorithmException {
        getTransAlgoMap();
        List<Customer> customerList = (List<Customer>) objectList;
        if(null == objectList || customerList.size() <= 0){
            return null;
        }

        try {
            List<CustomerReward> customerRewards = new ArrayList<>();
            for (int i = 0; i <customerList.size() ; i++) {
                CustomerReward customerReward = calculateCustReward((Customer)customerList.get(i));
                customerRewards.add(customerReward);
            }
            return customerRewards;

        }catch (Exception e){
            new AlgorithmException(e.getMessage());
        }
        return null;
    }











    /*************************************************Private Methods****************************************************/
    /**
     * This metho reads the config file and mapas them in Map<String, List<RewardCondition>> where @{@link RewardCondition} represents each condition
     *
     * the  config properties read by this method are
     *
     * batch.algorithm.rules.transaction.types: ecom, remaining
     * batch.algorithm.rules.transaction.ecom: 0-2.49*0, 2.50-4.99*1, 5.00-9.99*2, 10.00-99.99*3, 100.00-299.99*7*%, 300.00-999.99*10*%, 1000-0*15*%
     * batch.algorithm.rules.transaction.remaining: 0-2.49*1, 2.50-4.99*2, 5.00-9.99*3, 10.00-99.99*4, 100.00-299.99*9*%, 300.00-999.99*11*%, 1000-0*13*%
     *
     * @return @Map<String, List<RewardCondition>>
     */

    private Map<String, List<RewardCondition>> getTransAlgoMap() {
        if(null != rewardConditionListMap)
            return rewardConditionListMap;
        try {

            Map<String, List<RewardCondition>>  transMap = new Hashtable<>();
            if(null == transactionTypes || transactionTypes.length == 0 ){
                return null;
            }
            //        batch.algorithm.rules.transaction.ecom
            final String transBaseProperty = "batch.algorithm.rules.transaction.";
            for (int i=0; i<transactionTypes.length; i++){
                String transConfigStr = env.getProperty(transBaseProperty+transactionTypes[i]);
                if (null == transConfigStr || transConfigStr.isEmpty())
                    continue;
                String [] transTokens = transConfigStr.split(",");
                if(null == transTokens || transTokens.length == 0)
                    continue;


                final String transType = transactionTypes[i];
                //            rewardCondition.

                List<RewardCondition> rewardConditionList = new ArrayList<>();
                for (int j=0; j<transTokens.length; j++){
                    String transactionCondition = transTokens[j];//100.00-299.99*7*%
                    if(transactionCondition.isEmpty())
                        continue;

                    RewardCondition rewardCondition = new RewardCondition();
                    rewardCondition.setTransactionType(transType);
                    String [] transConditions = transactionCondition.split("-");
                    double startAmount = Double.valueOf(transConditions[0]);//100.000
                    rewardCondition.setStartAmount(startAmount);

                    String secondCond = transConditions[1];
                    if (secondCond.isEmpty())
                        continue;

                    String amountConds[] = secondCond.split("\\*");//299.99*7*%
                    if(amountConds.length <=1)
                        continue;
                    double secondAmount = Double.valueOf(amountConds[0]);
                    rewardCondition.setEndAmount(secondAmount);

                    double reward = 0;
                    if(amountConds.length > 1)
                        reward = Double.valueOf(amountConds[1]);
                    rewardCondition.setReward(reward);

                    String condition = "";
                    if(amountConds.length > 2)
                        condition = amountConds[2];
                    rewardCondition.setCondition(condition);
                    rewardConditionList.add(rewardCondition);
                    //100.00-299.99*7*%

                }
                transMap.put(transType,rewardConditionList);
            }
            rewardConditionListMap = transMap;
            return rewardConditionListMap;
        }catch (Exception e){
            //throw  new AlgorithmException(e.getMessage());
            System.err.println(e.getMessage());
        }
        return rewardConditionListMap;
    }



    /**
     * @author Abdulrehman
     * @apiNote Thos method returns the merchant multiplexer like if merchant type is Airlines then the reward will be multiplexed with two.
     * marchantFactorListObj is string array fetched from application.properties file which contains the merchant factor like
     * 'merchant-type*multiplexer double value' as its comma separated list.
     * This method parses it and returns the multiplexer. If transaction type is not found in list then it return 1
     * @param marchantFactorListObj marchantFactorListObj is string array fetched from application.properties file which contains the merchant factor like
     *                              'merchant-type*multiplexer double value' as its comma separated list
     * @param merchanType Merchant typle like 'Airlines'
     * @return This method parses it and returns the multiplexer. If transaction type is not found in list then it return 1
     */
    private double getMerchantFactor(String [] marchantFactorListObj, String merchanType){
        double merchantMultiplexer = 1;
        if(marchantFactorListObj.length <= 0){
            return merchantMultiplexer;
        }
        for (int i = 0; i < marchantFactorListObj.length; i++) {
            String factStr = marchantFactorListObj[i];
            if(factStr.toLowerCase().contains(merchanType.toLowerCase())){
                String [] splitedStr = factStr.split("\\*");
                if(splitedStr.length>0){
                    merchantMultiplexer = Double.valueOf(splitedStr[1]);
                    break;
                }
            }
        }
        return merchantMultiplexer;
    }

    private CustomerReward calculateCustReward(Customer customer){
        try {
            String transType = customer.getTransaction_type();
            if(transType.isEmpty()){
                return null;
            }
            List<RewardCondition> rewardConditionList = rewardConditionListMap.get(transType.toLowerCase());
            if(null ==rewardConditionList || rewardConditionList.size() == 0){
                rewardConditionList = rewardConditionListMap.get("remaining");
                if(null ==rewardConditionList || rewardConditionList.size() == 0){
                    return null;
                }

            }

            double transAmount = Double.valueOf(customer.getTransaction_amount());
            double reward = getReward(rewardConditionList, transAmount);

            double merchantFactor = getMerchantFactor(marchantFactorList, customer.getMerchant_type());
            if((reward < 0 || reward > 0)  && merchantFactor > 1){
                reward = reward*merchantFactor;
            }
//            if (reward >0){
               CustomerReward customerReward =  customerRewardMap.get(customer.getCustomer_id());
               if(null != customerReward){
                   reward = customerReward.getReward_points()+reward;
                   customerReward.setReward_points(Integer.valueOf(String.valueOf(Math.round(reward))));
               }else {
                   customerReward = new CustomerReward();
                   customerReward.setReward_points(Integer.valueOf(String.valueOf(Math.round(reward))));
                   customerReward.setBank_name(customer.getBank_name());
                   customerReward.setCustomer_name(customer.getCustomer_name());
                   customerReward.setCustomer_id(customer.getCustomer_id());
                   customerReward.setOrg(metaData.get("clientName"));
               }
                customerRewardMap.put(customer.getCustomer_id(),customerReward);
                return customerReward;
//            }


        }catch (Exception e){
            new AlgorithmException(e.getMessage());
        }
        return null;
    }

    private double getReward(List<RewardCondition>  rewardConditions, double amount){
       return ICalculator.getReward(rewardConditions, amount);
    }

    /**
     * Returns the calculated stats
     *
     * @return
     */
    @Override
    public Object getStats() {
        Set<String> keys = customerRewardMap.keySet();
        for(String key: keys){
            if(customerRewardMap.get(key).getReward_points() < 0){
                customerRewardMap.get(key).setReward_points(0);
            }
        }
            return customerRewardMap;
    }

    /**
     * Flushes the all stats stored in algorithm during the processing
     *
     * @throws AlgorithmException
     */
    @Override
    public void flush() throws AlgorithmException {

    }
}
