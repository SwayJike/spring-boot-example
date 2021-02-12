package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.annotation.Desensitization;
import com.jourwon.spring.boot.enums.DesensitionTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 脱敏服务接口
 *
 * @author JourWon
 * @date 2021/2/12
 */
public interface DesensitizeService {

    /**
     * 截断方法
     *
     * @param attachs 附件
     * @return List<String>
     */
    default List<String> truncateRender(String[] attachs) {
        List<String> regular = new ArrayList<>();
        if (null != attachs && attachs.length > 1) {
            String rule = attachs[0];
            String size = attachs[1];
            String template, result;
            String zero = "0";
            String one = "1";
            if (zero.equals(rule)) {
                template = "^(\\S{%s})(\\S+)$";
                result = "$1";
            } else if (one.equals(rule)) {
                template = "^(\\S+)(\\S{%s})$";
                result = "$2";
            } else {
                return regular;
            }
            if (Integer.parseInt(size) > 0) {
                regular.add(0, String.format(template, size));
                regular.add(1, result);
            }
        }
        return regular;
    }

    /**
     * 脱敏方法
     *
     * @param desensitionTypeEnum 脱敏类型枚举
     * @param desensitization 脱敏注解
     * @return List<String>
     */
    default List<String> desensitize(DesensitionTypeEnum desensitionTypeEnum, Desensitization desensitization) {
        List<String> regular;
        switch (desensitionTypeEnum) {
            case CUSTOM:
                regular = Arrays.asList(desensitization.attach());
                break;
            case TRUNCATE:
                regular = this.truncateRender(desensitization.attach());
                break;
            default:
                regular = Arrays.asList(desensitionTypeEnum.getRegular());
        }
        return regular;
    }

}
