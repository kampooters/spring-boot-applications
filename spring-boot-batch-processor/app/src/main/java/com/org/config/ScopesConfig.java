package com.org.config;

import com.org.batch.algorithm.IAlgorithm;
import com.org.batch.algorithm.RewardCalculatorAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author abdul.rehman4
 * @apiNote @{@link ScopesConfig} define the scopes for Spring beans like here
 * we are dfining the scopes for @{@link IAlgorithm} @{@link RewardCalculatorAlgorithm}.
 * We will use the request scope because we want single obect per HTTP request
 */
@Configuration
public class ScopesConfig {

    /**
     * @return Single {@link IAlgorithm} @{@link RewardCalculatorAlgorithm} Object per APplication
     */
    @Bean
    @Scope("singleton")
    public IAlgorithm algorithmSingleton() {
        return new RewardCalculatorAlgorithm();
    }

    /**
     * @return Single {@link IAlgorithm} @{@link RewardCalculatorAlgorithm} Object per instanciation call
     */
    @Bean
    @Scope("prototype")
    public IAlgorithm algorithmPrototype() {
        return new RewardCalculatorAlgorithm();
    }


    /**
     * @return Single {@link IAlgorithm} @{@link RewardCalculatorAlgorithm} Object per Http Request
     */
    @Bean
    @RequestScope
    public IAlgorithm requestScopedBean() {
        return new RewardCalculatorAlgorithm();
    }
}
