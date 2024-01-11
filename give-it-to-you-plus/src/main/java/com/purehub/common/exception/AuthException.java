package com.purehub.common.exception;


import com.purehub.common.result.RCodeEnum;
import lombok.Data;

/**
 * 自定义全局异常类
 */
@Data
public class AuthException extends RuntimeException {

    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param code
     * @param message
     */
    public AuthException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     *
     * @param RCodeEnum
     */
    public AuthException(RCodeEnum RCodeEnum) {
        super(RCodeEnum.getMessage());
        this.code = RCodeEnum.getCode();
        this.message = RCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "AuthException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
