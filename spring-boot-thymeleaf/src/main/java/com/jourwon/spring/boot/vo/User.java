package com.jourwon.spring.boot.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author JourWon
 * @date 2021/2/17
 */
public class User implements Serializable {

    private static final long serialVersionUID = -8645824245086423173L;

    String username;
    String password;
    List<String> hobbies;
    Map<String, String> secrets;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, String> getSecrets() {
        return secrets;
    }

    public void setSecrets(Map<String, String> secrets) {
        this.secrets = secrets;
    }

}
