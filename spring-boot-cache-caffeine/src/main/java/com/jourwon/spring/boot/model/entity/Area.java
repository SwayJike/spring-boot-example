package com.jourwon.spring.boot.model.entity;

import lombok.*;

/**
 * 行政区划
 *
 * @author JourWon
 * @date 2021/10/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area {

    /**
     * 地区名
     */
    private String name;

    /**
     * 地区名(简称)
     */
    private String shortName;

    /**
     * 地区编码
     */
    private String areaCode;

}