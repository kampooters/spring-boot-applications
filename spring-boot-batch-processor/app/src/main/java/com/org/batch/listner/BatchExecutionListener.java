package com.org.batch.listner;


import com.org.batch.Customer;
import com.org.batch.algorithm.IAlgorithm;
import com.org.persistence.mapper.CustomerRewardMapper;
import com.org.persistence.model.CustomerReward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author abdul.rehman4
 * @apiNote @{@link BatchExecutionListener}registers the all listners for Spring batch job.
 * the registered listeners are beforeStep, afterStep, skipWrite, skipRead
 */
@Component
public class BatchExecutionListener {

    /**
     * Injects request scope dependency like single object per HTTP request
     */
    @Resource(name = "requestScopedBean")
    IAlgorithm iAlgorithm;

    @Autowired
    CustomerRewardMapper customerRewardMapper;
    private static final Logger logger= LoggerFactory.getLogger(BatchExecutionListener.class);


    /**
     * @author Abdulrehman
     * beforeStep is invoked before the batch job start. Here we registers the all metadata related to the job
     * like fileth, clientName and other configurations
     * @param stepExecution Context object for Job from which every thing related to Job can be fetched
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        String clientName = jobParameters.getString("clientName");

        Map<String, String> stringStringMap = new Hashtable<>();
        stringStringMap.put("clientName", clientName);
        iAlgorithm.setMetaData(stringStringMap);
        customerRewardMapper.deleteByOrg(clientName);
    }

    /**
     * @author Abdulrehmando teh post required data like insertion of calculated rewards
     * @param stepExecution Context object for Job from which every thing related to Job can be fetched
     */
    @AfterStep
    public void AfterStep(StepExecution stepExecution) {

        final String exitCode = stepExecution.getExitStatus().getExitCode();
        String exitDesc = stepExecution.getExitStatus().getExitDescription();
        if (!StringUtils.isEmpty(exitDesc) && exitDesc.length() > 1000) {
            exitDesc = exitDesc.substring(0, 950);
        }

        Map<String, CustomerReward> customerRewardMap = (Map<String, CustomerReward> ) iAlgorithm.getStats();

        ArrayList<CustomerReward> rewardList = customerRewardMap.values().stream().collect(Collectors.toCollection(ArrayList::new));
        customerRewardMapper.insertRewardList(rewardList);

        try {
            JobParameters jobParameters = stepExecution.getJobParameters();
            long deleteFile = jobParameters.getLong("deleteFile");
            if(deleteFile > 0){
                String filePath = jobParameters.getString("filePath");
                File f1 = new File(filePath);
                f1.delete();
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }

        System.out.println("File reading finished with code:"+exitCode);

    }

    @OnSkipInWrite
    public void skipWrite(Customer Customer, Exception ex){
        logger.error("Exception in write: " + Customer.toString() + "\n" + ex.getMessage());
    }

    @OnSkipInRead
    public void skipRead(Exception ex){
        logger.error("Exception in read: "+"\n"+ex.getMessage());
    }


}
