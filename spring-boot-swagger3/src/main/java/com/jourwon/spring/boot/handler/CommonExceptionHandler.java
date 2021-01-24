package com.jourwon.spring.boot.handler;

import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import com.jourwon.spring.boot.exception.CommonException;
import com.jourwon.spring.boot.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 全局的异常处理
 *
 * @author wangyong
 * @date 2020/12/3
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 顶级的异常处理
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public CommonResponse<?> handleException(Exception e) {
        log.error("[Exception 异常]", e);
        return new CommonResponse<>(CommonResponseCodeEnum.SYS_EXCEPTION);
    }

    /**
     * 自定义的异常处理
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({CommonException.class})
    public CommonResponse<?> serviceExceptionHandler(CommonException e) {
        log.info("[CommonException 异常]", e);
        return new CommonResponse<>(e.getCode(), e.getMessage());
    }

    /**
     * 嵌套servlet异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({NestedServletException.class})
    public CommonResponse<?> handleNestedServletException(NestedServletException e) {
        log.error("[NestedServletException 异常]", e);
        return new CommonResponse<>(CommonResponseCodeEnum.SYS_EXCEPTION);
    }

    /**
     * restClient异常处理
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({RestClientException.class})
    public CommonResponse<?> restClientExceptionHandler(RestClientException e) {
        log.error("[RestClientException 异常]", e);
        return new CommonResponse<>(CommonResponseCodeEnum.SYS_EXCEPTION);
    }

    /**
     * 请求参数解析失败时抛出的异常，比如传入和接受的参数类型不一致
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public CommonResponse<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.info("[handleHttpMessageNotReadableException] 请求参数解析失败：", e);
        return new CommonResponse<>(CommonResponseCodeEnum.REQUEST_PARAMETER_ILLEGAL);
    }

    /**
     * 请求参数验证失败时抛出的异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = getBindResultMessage(bindingResult);
        log.info("[handleMethodArgumentNotValidException] 请求参数验证失败：" + message);
        return new CommonResponse<>(CommonResponseCodeEnum.REQUEST_PARAMETER_ILLEGAL, message);
    }

    /**
     * 获取绑定结果信息
     *
     * @param bindingResult 绑定结果
     * @return String 绑定结果信息
     */
    private String getBindResultMessage(BindingResult bindingResult) {
        FieldError error = bindingResult.getFieldError();
        String field = error != null ? error.getField() : "空";
        String code = error != null ? error.getDefaultMessage() : "空";
        return String.format("%s:%s", field, code);
    }

    /**
     * 参数绑定失败时抛出的异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public CommonResponse<?> handleHttpMessageNotReadableException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = getBindResultMessage(bindingResult);
        log.info("[handleHttpMessageNotReadableException] 参数绑定失败：" + message);
        return new CommonResponse<>(CommonResponseCodeEnum.REQUEST_PARAMETER_ILLEGAL, message);
    }

    /**
     * javax.validation 下校验参数时抛出的异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ValidationException.class})
    public CommonResponse<?> handleValidationException(ValidationException e) {
        log.info("[handleValidationException] 参数验证失败：", e);
        return new CommonResponse<>(CommonResponseCodeEnum.REQUEST_PARAMETER_ILLEGAL, e.getMessage());
    }


    /**
     * 缺少servlet请求参数时抛出的异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public CommonResponse<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("[handleMissingServletRequestParameterException] 缺少参数: " + e.getParameterName());
        return new CommonResponse<>(CommonResponseCodeEnum.REQUEST_PARAMETER_ILLEGAL, "缺少参数：" + e.getParameterName());
    }

    /**
     * javax.validation:validation-api 参数验证失败时抛出的异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public CommonResponse<?> handleServiceException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        log.info("[handleServiceException] 参数验证失败：" + message);
        return new CommonResponse<>(CommonResponseCodeEnum.REQUEST_PARAMETER_ILLEGAL, message);
    }

    /**
     * 方法参数类型不匹配异常时抛出的异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public CommonResponse<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.info("[handleMethodArgumentTypeMismatchException] 方法参数类型不匹配异常: ", e);
        return new CommonResponse<>(CommonResponseCodeEnum.REQUEST_PARAMETER_ILLEGAL);
    }

    /**
     * 不支持当前请求方法时抛出的异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public CommonResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.info("[handleHttpRequestMethodNotSupportedException] 不支持当前请求方法: ", e);
        return new CommonResponse<>(CommonResponseCodeEnum.NONSUPPORT_REQUEST_TYPE);
    }

    /**
     * 不支持当前媒体类型时抛出的异常
     *
     * @param e 异常
     * @return CommonResponse 统一返回前端的响应对象
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public CommonResponse<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.info("[handleHttpMediaTypeNotSupportedException] 不支持当前媒体类型: ", e);
        return new CommonResponse<>(CommonResponseCodeEnum.REQUEST_PARAMETER_ILLEGAL);
    }

}

