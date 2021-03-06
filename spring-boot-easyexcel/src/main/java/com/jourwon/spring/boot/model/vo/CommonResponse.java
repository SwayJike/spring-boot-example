package com.jourwon.spring.boot.model.vo;

import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回前端的响应对象
 *
 * @author JourWon
 * @date 2021/1/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CommonResponse-统一返回前端的响应对象")
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = -1338376281028943181L;

    @ApiModelProperty(value = "响应编码")
    private String code;

    @ApiModelProperty(value = "响应信息")
    private String message;

    @ApiModelProperty(value = "业务数据")
    private T data;

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(CommonResponseCodeEnum commonResponseCodeEnum) {
        this.code = commonResponseCodeEnum.getCode();
        this.message = commonResponseCodeEnum.getMessage();
    }

    public CommonResponse(T data) {
        this.code = CommonResponseCodeEnum.SUCCESS.getCode();
        this.message = CommonResponseCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public CommonResponse(CommonResponseCodeEnum commonResponseCodeEnum, T data) {
        this.code = commonResponseCodeEnum.getCode();
        this.message = commonResponseCodeEnum.getMessage();
        this.data = data;
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(CommonResponseCodeEnum.SUCCESS);
    }

    public static <T> CommonResponse<T> success(String message) {
        return new CommonResponse<>(CommonResponseCodeEnum.SUCCESS.getCode(), message);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(CommonResponseCodeEnum.SUCCESS, data);
    }

    public static <T> CommonResponse<T> success(CommonResponseCodeEnum commonResponseCodeEnum, T data) {
        return new CommonResponse<>(commonResponseCodeEnum, data);
    }

    public static <T> CommonResponse<T> failure(CommonResponseCodeEnum commonResponseCodeEnum) {
        return new CommonResponse<>(commonResponseCodeEnum);
    }

    public static <T> CommonResponse<T> failure(CommonResponseCodeEnum commonResponseCodeEnum, T data) {
        return new CommonResponse<>(commonResponseCodeEnum, data);
    }

    public static <T> CommonResponse<T> failure(String code, String message) {
        return new CommonResponse<>(code, message);
    }

}
