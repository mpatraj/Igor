package com.example.RestService.dao;
//This class is used for postgres implementation. To connect to another data base with changing
//just one line of code
//Dependency injection

import com.example.RestService.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository("postgres") //To change implementation all we have to do to imput this name in Server
public class CarDataAccessService implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(Person person) {
        int userId = person.getUserId();
        String carNum = person.getCarNum();
        Boolean approvedStatus = false;
        Timestamp carAddedTime = new Timestamp(System.currentTimeMillis());//person.getCarAddedTime();
        Timestamp carApprovedTime = null;//person.getCarApprovedTime();
        log.debug(String.valueOf(carAddedTime));
        String sql = "INSERT INTO person (" +
                "userId, " +
                "carNum, " +
                "carAddedTime, " +
                "approvedStatus, " +
                "carApprovedTime) " +
                "VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                userId,
                carNum,
                carAddedTime,
                approvedStatus,
                carApprovedTime
        );
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "" +
                "SELECT " +
                "userId, " +
                "carNum, " +
                "carAddedTime, " +
                "approvedStatus, " +
                "carApprovedTime " +
                "FROM person";
        return jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    int userId = resultSet.getInt("userId");
                    String carNum = resultSet.getString("carNum");
                    Timestamp carAddedTime = resultSet.getTimestamp("carAddedTime");
                    Boolean approvedStatus = resultSet.getBoolean("approvedStatus");
                    Timestamp carApprovedTime = resultSet.getTimestamp("carApprovedTime");
                    return new Person(userId, carNum, approvedStatus, carAddedTime, carApprovedTime);
                });
    }

    @Override
    public Optional<Person> selectPersonByUserId(int userId) {
        final String sql = "" +
                "SELECT " +
                "userId, " +
                "carNum, " +
                "carAddedTime, " +
                "approvedStatus, " +
                "carApprovedTime " +
                "FROM person " +
                "WHERE userId = ?";
        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{userId},
                (resultSet, i) -> {
                    int personUserId = resultSet.getInt("userId");
                    String carNum = resultSet.getString("carNum");
                    Timestamp carAddedTime = resultSet.getTimestamp("carAddedTime");
                    Boolean approvedStatus = resultSet.getBoolean("approvedStatus");
                    Timestamp carApprovedTime = resultSet.getTimestamp("carApprovedTime");
                    return new Person(personUserId, carNum, approvedStatus, carAddedTime, carApprovedTime);
                });
        return Optional.ofNullable(person); //Because it can be null
    }

    @Override
    public int deletePersonByUserId(int userId) {
        String sql = "" +
                "DELETE FROM person " +
                "WHERE userId = ?";
        return jdbcTemplate.update(sql, userId);
    }

    @Override
    public int updateCarNumByUserId(int userId, Person person) {
        String sql = "" +
                "UPDATE person " +
                "SET carNum = ? , " +
                "carAddedTime = ?, " +
                "approvedStatus = false, " +
                "carApprovedTime = null " +
                "WHERE userId = ?";
        String carNum = person.getCarNum();
        Timestamp carAddedTime = new Timestamp(System.currentTimeMillis());
        return jdbcTemplate.update(sql, carNum, carAddedTime, userId);
    }

    @Override
    public int updateApprovedStatusByUserId(int userId, Person person) {
        String sql = "" +
                "UPDATE person " +
                "SET approvedStatus = ? , " +
                "carApprovedTime = ? " +
                "WHERE userId = ?";
        Boolean approvedStatus = person.getApprovedStatus();
        Timestamp carApprovedTime = new Timestamp(System.currentTimeMillis());
        return jdbcTemplate.update(sql, approvedStatus, carApprovedTime, userId);
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isCarNumTaken(String carNum) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM person " +
                " WHERE carNum = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{carNum},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isUserIdTaken(int userId) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM person " +
                " WHERE userId = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{userId},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }
}

