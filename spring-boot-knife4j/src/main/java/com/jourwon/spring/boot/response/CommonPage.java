package com.jourwon.spring.boot.response;

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
@ApiModel("CommonPage-分页数据结构")
public class CommonPage<T> implements Serializable {

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
     * 数据没有记录返回空页,定义为一个不可变的静态私有内部类保证线程安全
     */
    private static final CommonPage<?> EMPTY_PAGE = new CommonPage<>(1, 0, 0, 0, 0, Collections.emptyList());

    /**
     * 空分页数据
     *
     * @param <T> 空
     * @return CommonPage<T> 分页数据结构
     */
    @SuppressWarnings({"unused", "unchecked"})
    public static <T> CommonPage<T> emptyPage() {
        return (CommonPage<T>) EMPTY_PAGE;
    }

}
