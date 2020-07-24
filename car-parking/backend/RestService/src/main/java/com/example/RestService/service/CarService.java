package com.example.RestService.service;

import com.example.RestService.dao.CarDao;
import com.example.RestService.exception.ApiRequestException;
import com.example.RestService.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarDao carDao;

    @Autowired
    public CarService(@Qualifier("postgres") CarDao carDao) {
        this.carDao = carDao;
    }

    public void addPerson(Person person) {
        if (carDao.isCarNumTaken(person.getCarNum())) {
            throw new ApiRequestException(
                    "Car number " + person.getCarNum() + " already is in database"
            );
        }
        if (carDao.isUserIdTaken(person.getUserId())) {
            throw new ApiRequestException(
                    "User " + person.getUserId() + " already exists in database"
            );
        }
        try {

            carDao.insertPerson(person);
        } catch (Exception e){
            throw new ApiRequestException(
                    "Cant add user " + person.getUserId() + " to database"
            );
        }
    }

    public List<Person> getAllPeople() {
        return carDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(int userId) {
        try {
            return carDao.selectPersonByUserId(userId);
        } catch (Exception e) {
            throw new ApiRequestException("User " + userId + " not found in database");
        }
    }

    public void deletePerson(int userId) {
        carDao.deletePersonByUserId(userId);
    }

    public void updatePersonCarNum(int userId, Person newPerson) {
        if (carDao.isCarNumTaken(newPerson.getCarNum())) {
            throw new ApiRequestException(
                    "Car number " + newPerson.getCarNum() + " already is in database"
            );
        }
        if (!carDao.isUserIdTaken(userId)) {
            throw new ApiRequestException(
                    "User " + userId + " not found in database"
            );
        }
        carDao.updateCarNumByUserId(userId, newPerson);
    }

    public void updatePersonApprovedStatus(int userId, Person newPerson) {
        if (!carDao.isUserIdTaken(userId)) {
            throw new ApiRequestException(
                    "User " + userId + " not found in database"
            );
        }
        carDao.updateApprovedStatusByUserId(userId, newPerson);
    }
}
