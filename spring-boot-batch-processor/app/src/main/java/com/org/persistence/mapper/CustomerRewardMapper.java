package com.org.persistence.mapper;


import com.org.persistence.model.CustomerReward;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author abdul.rehman4
  * @version 1.0
 * @since v1.0
 *
 * {@link CustomerRewardMapper} work as {@link Mapper}  for model {@link CustomerReward}
 *
 * Implements the DAO pattern
 */

@Mapper
@Repository
public interface CustomerRewardMapper {

    @Select("SELECT * FROM customer_reward")
    List<CustomerReward> getAll();

    @Select("SELECT * FROM customer_reward where customer_id=#{customerId}")
    CustomerReward getById(long customerId);
    
    
    @Select("SELECT * FROM customer_reward where org=#{org}")
    List<CustomerReward> getByOrg(String org);

    @Insert("INSERT into customer_reward (customer_id, customer_name, bank_name, reward_points)" +
            "values(#{customer_id}, #{customer_name}, #{bank_name}, #{reward_points})")
    void insert(CustomerReward customerReward);

    @Insert({"<script>",
            "INSERT into customer_reward (customer_id, customer_name, bank_name, reward_points, org) " +
                    " values ",
            "<foreach collection='customer_reward' item='reward' index='index' open='(' separator = '),(' close=')' >" +
                    " #{ reward.customer_id}, #{ reward.customer_name}, #{ reward.bank_name}, #{ reward.reward_points}, #{ reward.org}  " +
                    "</foreach>",
            "</script>"})
    int insertRewardList(@Param("customer_reward") List<CustomerReward> customer_reward);

    @Delete("DELETE FROM customer_reward " +
            " where org=#{org}")
    void deleteByOrg(String org);



}
