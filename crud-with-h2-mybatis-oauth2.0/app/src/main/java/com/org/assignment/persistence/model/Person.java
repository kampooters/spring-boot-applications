package com.org.assignment.persistence.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Abdulrehman
 * {@link Person} works as model for the Person
 */
@ApiModel(value = "Person", description = "Works as person Model")
public class Person {

    @ApiModelProperty(name = "id", value = "Unique Identifier, its auto system generated",  required = false)
    private int id;

    @Size(min=1, message = "first_name = At least one character is required")
    @ApiModelProperty(name = "first_name", value = "First name",  required = true)
    @Valid
    private String first_name;

    @Size(min=1, message = "last_name = At least one character is required")
    @ApiModelProperty(name = "last_name", value = "Last name",  required = true)
    @Valid
    private String last_name;

    @ApiModelProperty(name = "age", value = "Age in Years",  required = true)
    @Valid
    private int age;


    @ApiModelProperty(name = "favourite_color", value = "Favourite color",  required = false)
    private String favourite_color;

    @ApiModelProperty(name = "hobby", value = "List of hobbies",  required = false)
    private List<String> hobby;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavourite_color() {
        return favourite_color;
    }

    public void setFavourite_color(String favourite_color) {
        this.favourite_color = favourite_color;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }
}
