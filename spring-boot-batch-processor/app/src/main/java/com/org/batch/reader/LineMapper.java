package com.org.batch.reader;

import com.org.batch.Customer;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

/**
 * @author abdul.rehman4
 * @apiNote @{@link LineMapper}maps the CSV headers to @{@link Customer} fields. After eah row read the row will
 * be mapped to @{@link Customer} object
 */
public class LineMapper extends DefaultLineMapper<Customer>  {
    public LineMapper(){
        setMetaData();
    }

    private void setMetaData(){
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"Transaction ID", "Customer ID", "Customer Name",
                "Bank Name", "Transaction Amount","Merchant Name","Merchant Type",
                "Transaction Country","Transaction Type"});
        lineTokenizer.setIncludedFields(new int[]{0, 1,2,3,4,5,6,7,8});
        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<Customer>();
        fieldSetMapper.setTargetType(Customer.class);
        setLineTokenizer(lineTokenizer);
        setFieldSetMapper(fieldSetMapper);
    }
}
