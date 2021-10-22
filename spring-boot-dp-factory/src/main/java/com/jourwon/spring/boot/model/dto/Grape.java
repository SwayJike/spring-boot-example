package com.jourwon.spring.boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 葡萄
 *
 * @author JourWon
 * @date 2021/10/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grape {

    /**
     * 名称
     */
    private String name;

    /**
     * 单价
     */
    private Integer price;

}
