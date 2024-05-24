package cn.yam.backmanage.service.impl;

import cn.yam.backmanage.entity.Response.UserResponse;
import cn.yam.backmanage.entity.enums.ResponseCodeEnum;
import cn.yam.backmanage.entity.pojo.User;
import cn.yam.backmanage.exception.UserException;
import cn.yam.backmanage.mappers.UserMapper;
import cn.yam.backmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 * 日期：2024/5/24 上午8:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void login(String username, String password) {

        // 用户名是否存在
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UserException(ResponseCodeEnum.USER_NOT_FOUND.getCode(), ResponseCodeEnum.USER_NOT_FOUND.getMessage());
        }

        // 密码是否正确
        if (!user.getPassword().equals(password)) {
            throw new UserException(ResponseCodeEnum.PASSWORD_ERROR.getCode(), ResponseCodeEnum.PASSWORD_ERROR.getMessage());
        }


    }

    @Override
    public void register(String username, String password) {
        // 校验用户名是否存在
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            throw new UserException(ResponseCodeEnum.USER_EXIST.getCode(), ResponseCodeEnum.USER_EXIST.getMessage());
        }
        User userTemp = new User();
        userTemp.setUsername(username);
        userTemp.setPassword(password);
        userMapper.insert(userTemp);


    }

    @Override

    public UserResponse findUsers(String search, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<User> users = userMapper.findUsers(search, offset, pageSize);
        int total = userMapper.countUsers(search);
        return new UserResponse(users, total);
    }
}