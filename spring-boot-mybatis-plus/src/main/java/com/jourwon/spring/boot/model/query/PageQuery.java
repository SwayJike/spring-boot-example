package com.jourwon.spring.boot.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页查询请求参数
 *
 * @author JourWon
 * @date 2021/1/22
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("PageQuery-分页查询请求参数")
public class PageQuery implements Serializable {

    private static final long serialVersionUID = -6363770969828711469L;

    @ApiModelProperty(value = "当前页码", required = true)
    @NotNull(message = "当前页码不能为空")
    @Min(value = 1, message = "当前页码不能小于1")
    private int pageNum;

    @ApiModelProperty(value = "分页大小", required = true)
    @NotNull(message = "分页大小不能为空！")
    @Min(value = 1, message = "分页大小不能小于1")
    @Max(value = 1000, message = "分页大小不能超过1000条数据")
    private int pageSize;

}
