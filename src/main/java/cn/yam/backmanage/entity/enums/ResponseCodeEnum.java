package cn.yam.backmanage.entity.enums;

public enum ResponseCodeEnum {
    SUCCESS(200, "成功"),
    ERROR(500, "失败"),
    PARAM_ERROR(400, "参数错误"),
    NOT_FOUND(404, "资源未找到"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    SERVER_ERROR(500, "服务器错误"),
    USER_NOT_FOUND(1001, "用户不存在"),
    PASSWORD_ERROR(1002,"密码错误" ),
    USER_EXIST(1003, "用户已存在");

    private Integer code;
    private String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
