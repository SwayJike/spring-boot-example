package com.jourwon.spring.boot.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author JourWon
 * @date 2021/1/18
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5156893569251495055L;

    private Long userId;

    private String username;

    private String mobilePhoneNumber;

    private String email;

    private Short deleteState;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public User() {
    }

    public User(Long userId, String username, String mobilePhoneNumber, String email, Short deleteState, LocalDateTime createTime, LocalDateTime updateTime) {
        this.userId = userId;
        this.username = username;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
        this.deleteState = deleteState;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Short deleteState) {
        this.deleteState = deleteState;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", deleteState=" + deleteState +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
