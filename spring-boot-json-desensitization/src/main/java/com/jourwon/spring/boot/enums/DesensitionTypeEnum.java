package com.jourwon.spring.boot.enums;

/**
 * 脱敏类型枚举
 *
 * @author JourWon
 * @date 2021/2/12
 */
public enum DesensitionTypeEnum {

    /**
     * 手机号脱敏
     * "(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}"
     * "(\\d{3})\\d{4}(\\d{4})"
     */
    PHONE("mobile", "11位手机号", "(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{4}(\\d{4})", "$1****$2"),
    IDENTITYNO("identityNo", "15或者18身份证号", "(\\w{4})\\w{7,10}(\\w{4})", "$1****$2"),
    BANKCARDNO("bankCardNo", "银行卡号", "(\\d{4})\\d*(\\d{4})", "$1****$2"),
    REALNAME("realname", "真实姓名Json类型", "(\"realname\":)(\"[\u4E00-\u9FA5]{1})[\u4E00-\u9FA5]{1,}(\")", "$1$2**$3"),
    REALNAME2("realname", "真实姓名toString类型", "(realname=)([\u4E00-\u9FA5]{1})[\u4E00-\u9FA5]{1,}", "$1$2**"),
    CUSTOM("custom", "自定义正则处理", ""),
    TRUNCATE("truncate", "字符串截取处理", ""),
    ;
    String type;
    String describe;
    String[] regular;

    DesensitionTypeEnum(String type, String describe, String... regular) {
        this.type = type;
        this.describe = describe;
        this.regular = regular;
    }

    public String getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }

    public String[] getRegular() {
        return regular;
    }

}
