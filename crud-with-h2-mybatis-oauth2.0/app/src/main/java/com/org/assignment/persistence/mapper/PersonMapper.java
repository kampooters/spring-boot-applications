package com.org.assignment.persistence.mapper;


import com.org.assignment.persistence.model.Person;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author abdul.rehman4
  * @version 1.0
 * @since v1.0
 *
 * {@link PersonMapper} work as {@link Mapper}  for model {@link Person}
 *
 * Implements the DAO pattern
 */

@Mapper
@Repository
public interface PersonMapper {

    @Select("SELECT * FROM person_table")
    List<Person> getAll();

    @Select("SELECT * FROM person_table where person_id=#{personId}")
    Person getById(long personId);

    @Select("SELECT * FROM person_table where first_name=#{first_name} AND  first_name=#{first_name} AND  favourite_color=#{favourite_color}")
    List<Person> search(Person person);


    @Insert("INSERT into person_table (first_name, last_name, age, favourite_color)" +
            "values(#{first_name}, #{last_name}, #{age}, #{favourite_color})")
    void insert(Person person);

    @Insert("INSERT into person_table (first_name, last_name, age, favourite_color)" +
                    "values(#{first_name}, #{last_name}, #{age}, #{favourite_color})" )
    @SelectKey(statement="call identity()", keyProperty="id", before=false, resultType= int.class)
    int insertWithId(Person person);

    @Update("UPDATE person_table set first_name=#{first_name}, last_name=#{last_name}, age=#{age}, favourite_color=#{favourite_color} " +
            " where id=#{id}")
    void update(Person person);

    @Delete("DELETE FROM person_table " +
            " where id=#{id}")
    void delete(String id);


    @Delete("DELETE person_table, hobby FROM person_table " +
            " INNER JOIN hobby " +
            "where person_table.id = hobby.person_id AND person_table.id = 2")
    void deleteCascade(String personId);


    @Insert({"<script>",
            "INSERT into hobby (person_id, name) " +
                    " values ",
            "<foreach collection='hobbyList' item='hobby' index='index' open='(' separator = '),(' close=')' >" +
                    "#{ personId}, #{ hobby}" +
                    "</foreach>",
            "</script>"})
    int insertHobbies(@Param("hobbyList") List<String> hobbyList, String personId);

    @Update("DELETE FROM hobby " +
            " where person_id=#{personId}")
    void deleteHobby(String personId);

    @Select("Select id,first_name, last_name, age, favourite_color from person_table where id=#{personId}")
    @Results(value = { @Result(property = "id", column = "id"), @Result(property = "first_name", column = "first_name"),
            @Result(property = "last_name", column = "last_name"),@Result(property = "age", column = "age"),
            @Result(property = "favourite_color", column = "favourite_color"),
            @Result(property = "hobby", javaType = List.class, column = "id", many = @Many(select = "getHobbies"))

    })
    public Person getPersonById(int personId);

    @Select("select name from hobby where person_id=#{personId}")
    public List<String> getHobbies(Integer personId);


}
