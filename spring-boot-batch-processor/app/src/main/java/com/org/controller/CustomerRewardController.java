package com.org.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.org.constant.BASERoute;
import com.org.constant.CustomerRewardRoute;
import com.org.dto.HttpResonseDTO;
import com.org.logger.LogManager;
import com.org.persistence.model.CustomerReward;
import com.org.persistence.service.impl.CustomerRewardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Abdulrehman
 * @apiNote {@link CustomerRewardController} exposes REST API to calculate the rewards for customers
 *
 */

@Api( description = "Customer Reward Controller", value = "Controller exposes REST API to calculate the rewards for customers")
@RestController
@RequestMapping(BASERoute.BASE_ROUTE)
public class CustomerRewardController extends LogManager {

    public CustomerRewardController(){
        super(CustomerRewardController.class);
    }

    @Autowired
    private CustomerRewardService customerRewardService;

    @ApiOperation(value = "Process CSV from uploaded file", notes = "This API uploads the .csv file and processes it to calculate the rewards. Make sure the CSV contains the Headers " +
            "<br />  **CSV Headers** Transaction ID, Customer ID, Customer Name, Bank Name, Transaction Amount (EUR), Merchant Name, Merchant Type, Transaction Country, Transaction Type <br />" +
            " <br /> ** File extension** .csv" +
            "<br /> **File Size:** The CSV file size limit is 5MB, if size increases from 5MB then the server will not accept and process. If the size is large then FTP the file " +
            "on server and use the API /customer/process/csv/path")
    @ApiResponse(code = 200, message = "API response")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "client-name", value = "Name of client saved as key as well, on which beses later on the rewards can be fetched", paramType = "header"),
                    @ApiImplicitParam(name = "Authorization", value = "(If oAuth is enabled) oAuth access token, The authentication token will always will come in header", paramType = "header"),
                    @ApiImplicitParam(name = "ClientId", value = "(If oAuth is enabled) Client id that was used at the time of registering.", paramType = "header")})

    @PostMapping(path = CustomerRewardRoute.PROCESS_CSV_UPLOAD)
    public HttpResonseDTO saveProcess(MultipartFile screenImg, @RequestHeader (name="client-name") String clientName, HttpServletRequest request) {
        try{
            String filePath = request.getServletContext().getRealPath("/");
            filePath = filePath+"/"+screenImg.getOriginalFilename();
            File f1 = new File(filePath);
            screenImg.transferTo(f1);

            requestReceived(BASERoute.BASE_ROUTE+CustomerRewardRoute.PROCESS_CSV_UPLOAD, filePath);
            HttpResonseDTO resp =   customerRewardService.processCSV(filePath, clientName, 1);
            requestServed(BASERoute.BASE_ROUTE+CustomerRewardRoute.PROCESS_CSV_UPLOAD, resp);
            resp.setPath(BASERoute.BASE_ROUTE + CustomerRewardRoute.PROCESS_CSV_UPLOAD);
            return resp;

        }catch (IOException e) {
            error(e.getMessage());
        }
        return new HttpResonseDTO();
    }




    @ApiOperation(value = "Process CSV From Absolute Path", notes = "If the file size increase from **5MB** then use this API for processing." +
            "<br /> **Remote Server: ** If you are on remote server then FTP the file on any loaction on server which app is deployed and provide teh absolue path in file_path Header." +
            "<br /> **Local Server (Local Machine):** if you are on local server then provide the local file path " +
            "like (Windows) D:\\\\Source Code\\\\Workspaces\\\\Practise\\\\spring-assignment\\\\data\\\\transactions.csv " +
            "<br />  **CSV Headers** Transaction ID, Customer ID, Customer Name, Bank Name, Transaction Amount (EUR), Merchant Name, Merchant Type, Transaction Country, Transaction Type <br />" +
            " <br /> ** File extension** .csv" +
            "<br /> **File Size:** Large file like having billion of rows")
    @ApiResponse(code = 200, message = "API response")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "client", value = "Name of client saved as key as well, on which beses later on the rewards can be fetched", paramType = "header"),
                    @ApiImplicitParam(name = "file_path", value = "Absolute path to the CSV file (If you are on remote server then FTP the file on server and give the path." +
                            "If path string is in correct you will receive error message and CSV will not be processed", paramType = "header"),
                    @ApiImplicitParam(name = "Authorization", value = "(If oAuth is enabled) oAuth access token, The authentication token will always will come in header", paramType = "header"),
                    @ApiImplicitParam(name = "ClientId", value = "(If oAuth is enabled) Client id that was used at the time of registering.", paramType = "header")})

    @PostMapping(path = CustomerRewardRoute.PROCESS_CSV_PATH)
    public HttpResonseDTO processCsvPath(@RequestHeader (name="file_path") String filePath, @RequestHeader (name="client") String clientName) {
        requestReceived(BASERoute.BASE_ROUTE+CustomerRewardRoute.PROCESS_CSV_PATH, filePath);
        HttpResonseDTO resp =   customerRewardService.processCSV(filePath, clientName, 0);
        requestServed(BASERoute.BASE_ROUTE+CustomerRewardRoute.PROCESS_CSV_PATH, resp);
        resp.setPath(BASERoute.BASE_ROUTE + CustomerRewardRoute.PROCESS_CSV_PATH);
        return resp;
    }



    @ApiOperation(value = "Download Stats in CSV", notes = "This API provides the facility to download the calculated rewards in .csv format. " +
            "But you have to provide the client name against which the .csv file was processed")
    @ApiResponse(code = 200, message = "API response")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "client-name", value = "Name of client saved as key as well, on which bases later on the rewards can be fetched", paramType = "header"),
                    @ApiImplicitParam(name = "Authorization", value = "(If oAuth is enabled) oAuth access token, The authentication token will always will come in header", paramType = "header"),
                    @ApiImplicitParam(name = "ClientId", value = "(If oAuth is enabled) Client id that was used at the time of registering.", paramType = "header")})
    @GetMapping(value = CustomerRewardRoute.DOWNLOAD_CSV)
    public void downloadCSV(HttpServletResponse response,@RequestHeader (name="client-name") String clientName) throws IOException {

        requestReceived(BASERoute.BASE_ROUTE+CustomerRewardRoute.DOWNLOAD_CSV, clientName );
        HttpResonseDTO resp =  customerRewardService.getByOrg(clientName);

        String filename = clientName+"-calculated-rewards.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<CustomerReward> writer = new StatefulBeanToCsvBuilder<CustomerReward>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
        List<CustomerReward> listBooks = (ArrayList<CustomerReward>)resp.getData();

        try{
            writer.write(listBooks);//listBooks
            requestServed(BASERoute.BASE_ROUTE+CustomerRewardRoute.DOWNLOAD_CSV, resp );
        }catch (Exception e){
            error("Error during csv download: "+e.getMessage());

        }
    }



    @ApiOperation(value = "GET REWARDs", notes = "This API provides the reward list for provided client in JSON format")
    @ApiResponse(code = 200, message = "API response")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "oAuth access token, The authentication token will always will come in header", paramType = "header"),
            @ApiImplicitParam(name = "ClientId", value = "Client id that was used at the time of registering.", paramType = "header")})
    @GetMapping(path = CustomerRewardRoute.GET_REWARD_STATS, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResonseDTO getRewards(@RequestParam (name="client") String clientName) {
        requestReceived(BASERoute.BASE_ROUTE+CustomerRewardRoute.GET_REWARD_STATS, clientName );
        HttpResonseDTO resp =  customerRewardService.getByOrg(clientName);
        requestServed(BASERoute.BASE_ROUTE+CustomerRewardRoute.GET_REWARD_STATS, resp );
        resp.setPath(BASERoute.BASE_ROUTE + CustomerRewardRoute.GET_REWARD_STATS);
        return resp;
    }



    @ApiOperation(value = "Test API", notes = "Its testing API")
    @ApiResponse(code = 200, message = "API response")
    @GetMapping(path = CustomerRewardRoute.TEST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResonseDTO getAll() {
        requestReceived(BASERoute.BASE_ROUTE+CustomerRewardRoute.TEST, null );
        HttpResonseDTO resp =  new HttpResonseDTO();
        resp.setMessage("Working fine");
        requestServed(BASERoute.BASE_ROUTE+CustomerRewardRoute.TEST, resp);
        resp.setPath(BASERoute.BASE_ROUTE + CustomerRewardRoute.TEST);
        return resp;
    }


}
