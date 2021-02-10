package com.jourwon.spring.boot.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页数据结构
 *
 * @author JourWon
 * @date 2021/1/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("CommonPageVO-分页数据结构")
public class CommonPageVO<T> implements Serializable {

    @ApiModelProperty("当前页码,从1开始")
    private int pageNum;

    @ApiModelProperty("分页大小")
    private int pageSize;

    @ApiModelProperty("当前分页记录数")
    private int size;

    @ApiModelProperty("总页数")
    private int pages;

    @ApiModelProperty("总记录数")
    private long total;

    @ApiModelProperty("当前页列表")
    private List<T> list;

    /**
     * 空分页数据
     *
     * @param <T> 空
     * @return CommonPageVO<T> 分页数据结构
     */
    public static <T> CommonPageVO<T> emptyPage(int pageNum, int pageSize) {
        return new CommonPageVO<>(pageNum, pageSize, 0, 0, 0, Collections.emptyList());
    }

}
