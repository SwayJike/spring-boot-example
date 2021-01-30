package com.jourwon.spring.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户
 *
 * @author JourWon
 * @date 2021/1/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 5156893569251495055L;

    private Long userId;

    private String username;

    private String mobilePhoneNumber;

    private String email;

    private Short deleteState;

    private LocalDateTime createTime;

    private Date updateTime;

}
