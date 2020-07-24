package com.example.RestService.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;



@Entity
public class Person {

    @Id
    private int userId;
    private String carNum;
    private Boolean approvedStatus;
    private Timestamp carAddedTime;
    private Timestamp carApprovedTime;

    public Person() {
    }

    public Person(int userId, String carNum) {
        this.userId = userId;
        this.carNum = carNum;
        this.approvedStatus = false;
        this.carAddedTime = new Timestamp(System.currentTimeMillis());
        this.carApprovedTime = null;
    }

    public Person(int userId,
                  String carNum,
                  Boolean approvedStatus) {
        this.userId = userId;
        this.carNum = carNum;
        this.approvedStatus = approvedStatus;
        this.carAddedTime = new Timestamp(System.currentTimeMillis());
        this.carApprovedTime = new Timestamp(System.currentTimeMillis());
    }

    public Person(int userId,
                  String carNum,
                  Boolean approvedStatus,
                  Timestamp carAddedTime,
                  Timestamp carApprovedTime) {
        this.userId = userId;
        this.carNum = carNum;
        this.approvedStatus = approvedStatus;
        this.carAddedTime = carAddedTime;
        this.carApprovedTime = carApprovedTime;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public Boolean getApprovedStatus() {
        return approvedStatus;
    }

    public void setApprovedStatus(Boolean approvedStatus) {
        this.approvedStatus = approvedStatus;
    }


    public Timestamp getCarAddedTime() {
        return carAddedTime;
    }

    public void setCarAddedTime(Timestamp carAddedTime) {
        this.carAddedTime = carAddedTime;
    }

    public Timestamp getCarApprovedTime() {
        return carApprovedTime;
    }

    public void setCarApprovedTime(Timestamp carApprovedTime) {
        this.carApprovedTime = carApprovedTime;
    }
}
