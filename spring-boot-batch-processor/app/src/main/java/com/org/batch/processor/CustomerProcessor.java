package com.org.batch.processor;


import com.org.batch.Customer;
import com.org.batch.algorithm.IAlgorithm;
import com.org.batch.algorithm.RewardCalculatorAlgorithm;
import com.org.persistence.model.CustomerReward;
import io.swagger.annotations.Scope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abdul.rehman4
 * @apiNote @{@link CustomerProcessor} is the main processing class for records read from csv file.
 * process method is invoked after each row read and here we submits teh readed customer to {@link IAlgorithm}
 */
public class CustomerProcessor implements ItemProcessor<Customer, CustomerReward> {

    @Resource(name = "requestScopedBean")
    IAlgorithm iAlgorithm;

    @Override
    public CustomerReward process(Customer customer) throws Exception {
        try {
            List<Customer> customerList = new ArrayList<>();
            customerList.add(customer);
            iAlgorithm.calculate(customerList);
            return null;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
