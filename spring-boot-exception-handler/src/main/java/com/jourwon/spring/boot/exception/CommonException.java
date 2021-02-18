package com.jourwon.spring.boot.exception;

import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义通用异常
 *
 * @author JourWon
 * @date 2021/1/21
 */
@Getter
@Setter
public class CommonException extends RuntimeException {

    /**
     * 响应编码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public CommonException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CommonException(CommonResponseCodeEnum commonResponseCodeEnum) {
        super(commonResponseCodeEnum.getMessage());
        this.code = commonResponseCodeEnum.getCode();
        this.message = commonResponseCodeEnum.getMessage();
    }

    /**
     * 不记录异常栈信息
     *
     * @return Throwable
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
