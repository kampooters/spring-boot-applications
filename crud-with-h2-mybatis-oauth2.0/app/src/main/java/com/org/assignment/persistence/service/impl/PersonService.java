package com.org.assignment.persistence.service.impl;


import com.org.assignment.dto.HttpResonseDTO;
import com.org.assignment.persistence.model.Person;
import com.org.assignment.logger.ILogManager;
import com.org.assignment.logger.LogManager;
import com.org.assignment.persistence.mapper.PersonMapper;
import com.org.assignment.persistence.service.ICRUDOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * Provides implementation for {@link ICRUDOperation}
 */
@Service
public class PersonService implements ICRUDOperation {


//    private static final Logger logger = LogM.getLogger(NotificationService.class);
private static final ILogManager logger = new LogManager(PersonService.class);
    @Autowired
    private PersonMapper personMapper;


    @Override
    public HttpResonseDTO getAll() {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        try {
            List<Person> personList = personMapper.getAll();
            respDTO.setPersonList(personList);
            respDTO.setCode(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            respDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            respDTO.setError(e.getMessage());
        }
        return respDTO;
    }

    @Override
    public HttpResonseDTO getById(String id) {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        try {
            Person person = personMapper.getPersonById(Integer.valueOf(id));
            if(null != person){
                List<Person> personList = new ArrayList<>();
                personList.add(person);
                respDTO.setPersonList(personList);
            }

            respDTO.setCode(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            respDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            respDTO.setError(e.getMessage());
        }
        return respDTO;
    }

    @Override
    public HttpResonseDTO insert(Object obj) {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        try {
            Person person = (Person)obj;
            int id = personMapper.insertWithId(person);

//            Person personResult = personMapper.insertWithId(person);

            if(person.getId()>0){
                personMapper.insertHobbies(person.getHobby(), String.valueOf(person.getId()));
            }
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            respDTO.setPersonList(personList);
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
        try {
            Person person = (Person)object;
            if(null == person || Integer.valueOf(person.getId())<=0){
                throw new Exception("Id is missing");
            }
            personMapper.update(person);
            personMapper.insertHobbies(person.getHobby(),String.valueOf(person.getId()));

            respDTO.setCode(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            respDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            respDTO.setError(e.getMessage());
        }
        return respDTO;
    }




    @Override
    public HttpResonseDTO delete(Object object) {
        HttpResonseDTO respDTO = new HttpResonseDTO();
        try {
            String id = (String) object;
            if(Integer.valueOf(id)<=0){
                throw new Exception("Id is missing");
            }
            personMapper.delete(id);
            personMapper.deleteHobby(id);
            respDTO.setCode(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            respDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            respDTO.setError(e.getMessage());
        }
        return respDTO;
    }

    @Override
    public HttpResonseDTO search(Object object) {
        HttpResonseDTO respDTO = new HttpResonseDTO();

        return respDTO;
    }


}
