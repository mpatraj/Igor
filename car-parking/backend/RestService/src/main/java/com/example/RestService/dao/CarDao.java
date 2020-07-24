package com.example.RestService.dao;

import com.example.RestService.model.Person;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    int insertPerson(Person person);

    List<Person> selectAllPeople();

    Optional<Person> selectPersonByUserId(int userId);

    int deletePersonByUserId(int userId);

    int updateCarNumByUserId(int userId, Person person);

    int updateApprovedStatusByUserId(int userId, Person person);

    boolean isCarNumTaken(String carNum);

    boolean isUserIdTaken(int userId);

}
