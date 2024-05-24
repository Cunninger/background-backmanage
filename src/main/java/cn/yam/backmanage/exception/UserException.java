package cn.yam.backmanage.exception;

/**
 * 功能：
 * 日期：2024/5/24 上午8:45
 */
public class UserException extends RuntimeException{
    public UserException(Integer code,String message) {
        super(code+"_"+message);
    }
}