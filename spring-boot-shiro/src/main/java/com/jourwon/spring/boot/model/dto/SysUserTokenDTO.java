package com.jourwon.spring.boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JourWon
 * @date 2021/3/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserTokenDTO implements Serializable {

    private static final long serialVersionUID = -622323528401278495L;

    private String token;

    private Integer expires;

}
