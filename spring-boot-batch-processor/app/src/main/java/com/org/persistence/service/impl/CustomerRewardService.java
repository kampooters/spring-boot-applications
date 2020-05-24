package com.org.persistence.service.impl;


import com.org.dto.HttpResonseDTO;
import com.org.logger.ILogManager;
import com.org.logger.LogManager;
import com.org.persistence.mapper.CustomerRewardMapper;
import com.org.persistence.model.CustomerReward;
import com.org.persistence.service.ICRUDOperation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.List;

/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * Provides implementation for {@link ICRUDOperation}. This Service works as Business Layer for  Customer's reward functionality
 */
@Service
public class CustomerRewardService implements ICRUDOperation {


private static final ILogManager logger = new LogManager(CustomerRewardService.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    private CustomerRewardMapper customerRewardMapper;


    /**
     * @author Abdulrehman
     * @apiNote processes the csv file. First it checks either the path is correct and file exist or not.
     * If file and path are OK then starts the Spring batch job for submitted CSV wich processes file,
     * calculates the rewards an stores into database
     * @param path Path to the CSV file like 'D:\\Source Code\\Workspaces\\Practise\\spring-assignment\\data\\transactions.csv'
     * @param deleteFile either you want to delete or not. 1 for delete and 0 for not
     * @param clientName client Name for which teh customers are being evaluated
     * @return @{@link HttpResonseDTO}
     */
    public HttpResonseDTO processCSV(String path, String clientName, long deleteFile) {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        try {
            if(!isPathValid(path)){
                respDTO.setMessage("File path is not correct");
                respDTO.setCode(HttpStatus.NOT_FOUND.value());
                respDTO.setError("File path is not correct");
                return respDTO;
            }

            if(deleteFile >1){
                deleteFile = 1;
            }else if(deleteFile < 0){
                deleteFile = 0;
            }

            JobParameters parameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .addString("filePath", path)
                    .addString("clientName", clientName)
                    .addLong("deleteFile", deleteFile)
                    .toJobParameters();
            jobLauncher.run(job, parameters);


            respDTO.setMessage("CSV submitted for procesing");
            respDTO.setCode(HttpStatus.OK.value());

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            respDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            respDTO.setError(e.getMessage());
        }
        return respDTO;
    }


    /**
     * @author Abdulrehman
     * @apiNote fetches the customer's rewards on organization (Client Name) bases
     * @param org client Name for which teh customers are being evaluated
     * @return @{@link HttpResonseDTO}
     */
    public HttpResonseDTO getByOrg(String org) {
      return getById(org);
    }

    /**
     * @author Abdulrehman
     * @apiNote fetches the customer's rewards on organization (Client Name) bases. It works as client method
     * @param org client Name for which teh customers are being evaluated
     * @return @{@link HttpResonseDTO}
     */
    @Override
    public HttpResonseDTO getById(String org) {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        try {
            List<CustomerReward> customerReward = customerRewardMapper.getByOrg(org);
            if(null != customerReward){
                respDTO.setData(customerReward);
            }
            respDTO.setCode(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            respDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            respDTO.setError(e.getMessage());
        }
        return respDTO;
    }


    /**
     * @author Abdulrehman
     * @apiNote inserts the customer's rewards list
     * @param obj List<CustomerReward>
     * @return @{@link HttpResonseDTO}
     */
    @Override
    public HttpResonseDTO insert(Object obj) {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        try {
            List<CustomerReward> rewardList = (List<CustomerReward>)obj;
            customerRewardMapper.insertRewardList(rewardList);

            respDTO.setCode(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            respDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            respDTO.setError(e.getMessage());
        }
        return respDTO;
    }



    @Override
    public HttpResonseDTO update(Object object) {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        return respDTO;
    }




    @Override
    public HttpResonseDTO delete(Object object) {
        HttpResonseDTO respDTO = new HttpResonseDTO();

        return respDTO;
    }

    @Override
    public HttpResonseDTO search(Object object) {
        HttpResonseDTO respDTO = new HttpResonseDTO();

        return respDTO;
    }

    @Override
    public HttpResonseDTO getAll() {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        try {
            List<CustomerReward> rewardList = customerRewardMapper.getAll();
            respDTO.setData(rewardList);
            respDTO.setCode(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            respDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            respDTO.setError(e.getMessage());
        }
        return respDTO;
    }



    /*****************************************Private methods******************************************************/
    private  boolean isPathValid(String path) {

        try {
            File tempFile = new File(path);
            boolean exists = tempFile.exists();
            return exists;

        } catch (InvalidPathException ex) {
            return false;
        }
    }


}
