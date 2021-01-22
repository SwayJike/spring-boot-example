package com.jourwon.spring.boot.response;

import com.jourwon.spring.boot.enums.ResponseCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

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

    private static final String MDC_KEY = "traceId";

    @ApiModelProperty(value = "响应编码")
    private String code;

    @ApiModelProperty(value = "响应信息")
    private String message;

    @ApiModelProperty(value = "业务数据")
    private T data;

    @ApiModelProperty(value = "请求id")
    private String traceId = MDC.get(MDC_KEY);

    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMessage();
    }

    public CommonResponse(T data) {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.message = ResponseCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public CommonResponse(ResponseCodeEnum responseCodeEnum, T data) {
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMessage();
        this.data = data;
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS, data);
    }

    public static <T> CommonResponse<T> success(ResponseCodeEnum responseCodeEnum, T data) {
        return new CommonResponse<>(responseCodeEnum, data);
    }

    public static <T> CommonResponse<T> failure(ResponseCodeEnum responseCodeEnum) {
        return new CommonResponse<>(responseCodeEnum);
    }

    public static <T> CommonResponse<T> failure(ResponseCodeEnum responseCodeEnum, T data) {
        return new CommonResponse<>(responseCodeEnum, data);
    }

}
