package com.org.batch.writer;

import com.org.persistence.model.CustomerReward;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

/**
 * @author abdul.rehman4
 * @apiNote @{@link Writer} will write the provided object @{@link CustomerReward} to teh customer_reward table
 * through JDBC. Its important to not the @{@link Writer} is invoked after the whole chunk read and processing
 */
public class Writer extends JdbcBatchItemWriter<CustomerReward> {
    public Writer(@NotNull DataSource dataSource){
        setDataSource(dataSource);
        setMetaData();
    }

    private void setMetaData(){

        setSql("INSERT INTO customer_reward ( customer_id , customer_name ,bank_name , reward_points, organization) " +
                "values(:customer_id , :customer_name , :bank_name , :reward_points, :org) ");
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CustomerReward>());

    }
}
