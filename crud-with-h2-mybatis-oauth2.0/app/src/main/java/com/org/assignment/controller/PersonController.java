package com.org.assignment.controller;

import com.org.assignment.common.constants.BASERoute;
import com.org.assignment.common.constants.PersonRoute;
import com.org.assignment.dto.HttpResonseDTO;
import com.org.assignment.persistence.model.Person;
import com.org.assignment.persistence.service.impl.PersonService;
import com.org.assignment.logger.LogManager;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author Abdulrehman
 * {@link PersonController} exposes the endpoints to process the notifications related to SMS notifications
 */

@Api( description = "Person Controller", value = "Controller exposes REST API for Person Model CRUD operations")
@RestController
@RequestMapping(BASERoute.BASE_ROUTE)
public class PersonController extends LogManager {
    private final String baseUrl = BASERoute.BASE_ROUTE+"/person";

    public PersonController(){
        super(PersonController.class);
    }

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "Test API", notes = "Its testing API")
    @ApiResponse(code = 200, message = "API response")
    @GetMapping(path = PersonRoute.TEST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResonseDTO getAll() {
        requestReceived(BASERoute.BASE_ROUTE+PersonRoute.TEST, null );
        HttpResonseDTO resp =  new HttpResonseDTO();
        resp.setMessage("Working fine");
        requestServed(BASERoute.BASE_ROUTE+PersonRoute.TEST, resp);
        resp.setPath(BASERoute.BASE_ROUTE + PersonRoute.TEST);
        return resp;
    }

    @ApiOperation(value = "Create Person", notes = "This API inserts Person in database")
    @ApiResponse(code = 200, message = "API response")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "Authorization", value = "oAuth access token, The authentication token will always will come in header", paramType = "header"),
                    @ApiImplicitParam(name = "ClientId", value = "Client id that was used at the time of registering.", paramType = "header")})

    @PostMapping(path = PersonRoute.CREATE)
    public HttpResonseDTO add(@Valid @RequestBody Person person) {
        requestReceived(BASERoute.BASE_ROUTE+PersonRoute.CREATE, person);
        HttpResonseDTO resp =  personService.insert(person);
        requestServed(BASERoute.BASE_ROUTE+PersonRoute.CREATE, resp);
        resp.setPath(BASERoute.BASE_ROUTE + PersonRoute.CREATE);
        return resp;

    }


    @ApiOperation(value = "GET Person", notes = "This API fetches Person from database on id bases")
    @ApiResponse(code = 200, message = "API response")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "Authorization", value = "oAuth access token, The authentication token will always will come in header", paramType = "header"),
                    @ApiImplicitParam(name = "ClientId", value = "Client id that was used at the time of registering.", paramType = "header")})
    @GetMapping(path = PersonRoute.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResonseDTO getById(@PathVariable (name="id") String id) {
        requestReceived(BASERoute.BASE_ROUTE+PersonRoute.GET_BY_ID, id );
        HttpResonseDTO resp =  personService.getById(id);
        requestServed(BASERoute.BASE_ROUTE+PersonRoute.GET_BY_ID, resp );
        resp.setPath(BASERoute.BASE_ROUTE + PersonRoute.GET_BY_ID);
        return resp;
    }

    @ApiOperation(value = "Update Person", notes = "This API updates Person in database on id bases")
    @ApiResponse(code = 200, message = "API response")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "Authorization", value = "oAuth access token, The authentication token will always will come in header", paramType = "header"),
                    @ApiImplicitParam(name = "ClientId", value = "Client id that was used at the time of registering.", paramType = "header")})
    @PutMapping(path = PersonRoute.UPDATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResonseDTO update(@Valid @RequestBody Person person) {
        requestReceived(BASERoute.BASE_ROUTE+PersonRoute.UPDATE, person);
        HttpResonseDTO resp =  personService.update(person);
        requestServed(BASERoute.BASE_ROUTE+PersonRoute.UPDATE, resp);
        resp.setPath(BASERoute.BASE_ROUTE + PersonRoute.UPDATE);
        return resp;

    }

    @ApiOperation(value = "DELETE Person", notes = "This API Deletes Person from database on id bases")
    @ApiResponse(code = 200, message = "API response")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "Authorization", value = "oAuth access token, The authentication token will always will come in header", paramType = "header"),
                    @ApiImplicitParam(name = "ClientId", value = "Client id that was used at the time of registering.", paramType = "header")})
    @DeleteMapping(path = PersonRoute.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResonseDTO delete(@RequestHeader("id") String id) {
        requestReceived(BASERoute.BASE_ROUTE+PersonRoute.DELETE, id );
        HttpResonseDTO resp =  personService.delete(id);
        requestServed(BASERoute.BASE_ROUTE+PersonRoute.DELETE, resp );
        resp.setPath(BASERoute.BASE_ROUTE + PersonRoute.DELETE);
        return resp;
    }

}
