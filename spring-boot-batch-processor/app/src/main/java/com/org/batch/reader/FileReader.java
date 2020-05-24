package com.org.batch.reader;

import com.org.batch.Customer;
import com.org.batch.listner.BatchExecutionListener;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.FileSystemResource;

/**
 * @author abdul.rehman4
 * @apiNote  @{@link FileReader} is responsibe for reading the csv file. It reads the file chunck by chunck (chunk size configured in application.prop file)
 * It requires the file path and name and @{@link com.org.batch.reader.LineMapper} implementation which maps teh CSV row to Obect
 */
public class FileReader extends FlatFileItemReader<Customer>{


        public FileReader(org.springframework.batch.item.file.LineMapper<Customer> lineMapper, String fileName, int skipRecordCount) {
            setMetaData(lineMapper,fileName,skipRecordCount);
        }

        private void setMetaData(LineMapper<Customer> lineMapper, String fileName, int skipRecordCount) {
            FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<Customer>();
            try {
                setLineMapper(lineMapper);
                setLinesToSkip(skipRecordCount);
                setResource(new FileSystemResource(fileName));
            }catch (Exception e){
                new BatchExecutionListener().skipRead(e);
            }
        }

}
