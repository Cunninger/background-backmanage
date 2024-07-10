package cn.yam.backmanage.service.impl;

import cn.yam.backmanage.entity.Response.ImportUsers;
import cn.yam.backmanage.entity.Response.UserResponse;
import cn.yam.backmanage.entity.enums.ResponseCodeEnum;
import cn.yam.backmanage.entity.pojo.User;
import cn.yam.backmanage.exception.UserException;
import cn.yam.backmanage.mappers.UserMapper;
import cn.yam.backmanage.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 登录
     *
     * @param username
     * @param password
     */

    @Override
    public void login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();// 查询条件构造器
        queryWrapper.eq("username", username);         // 查询条件

        User user = userMapper.selectOne(queryWrapper);       // 查询
        if (user == null) {
            throw new UserException(ResponseCodeEnum.USER_NOT_FOUND.getCode(), ResponseCodeEnum.USER_NOT_FOUND.getMessage());
        }

    }

    /**
     * 注册用户
     *
     * @param username
     * @param password
     */

    @Override
    public void register(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();// 查询条件构造器
        queryWrapper.eq("username", username);         // 查询条件

        User user = userMapper.selectOne(queryWrapper);       // 查询
        if (user != null) {
            throw new UserException(ResponseCodeEnum.USER_EXIST.getCode(), ResponseCodeEnum.USER_EXIST.getMessage());

        }
    }

    /**
     * 删除用户
     *
     * @param userId
     */

    @Override
    public void deleteUser(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new UserException(ResponseCodeEnum.USER_NOT_FOUND.getCode(), ResponseCodeEnum.USER_NOT_FOUND.getMessage());
        }
        userMapper.deleteById(userId);
    }

    /**
     * 获取用户列表
     *
     * @param userPage
     * @param search
     * @return
     */

    @Override
    public IPage<User> getUsers(Page<User> userPage, String search) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.lambda().like(User::getUsername, search);
        }
        return userMapper.selectPage(userPage, queryWrapper);
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */

    @Override
    public User findUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void importUsers(List<User> users) {
        for (User user : users) {
            createUser(user);
        }
    }

    /**
     * 更新用户信息
     *
     * @param userId
     * @param user
     */
    @Override
    public void updateUser(Integer userId, User user) {
        User userTemp = userMapper.selectById(userId);
        if (userTemp == null) {
            throw new UserException(ResponseCodeEnum.USER_NOT_FOUND.getCode(), ResponseCodeEnum.USER_NOT_FOUND.getMessage());
        }
        userTemp.setUsername(user.getUsername());
        userTemp.setPassword(user.getPassword());
        userTemp.setRole(user.getRole());
        userTemp.setStatus(user.getStatus());
        userMapper.updateById(userTemp);
    }

    /**
     * 创建用户 如果用户存在则更新用户信息
     *
     * @param user
     */

    @Override
    public void createUser(User user) {
        User userTemp = userMapper.selectById(user.getUserId());
        if (userTemp != null) {
            updateUser(userTemp.getUserId(), userTemp);
        } else {

            userMapper.insert(user);
        }
    }


}

