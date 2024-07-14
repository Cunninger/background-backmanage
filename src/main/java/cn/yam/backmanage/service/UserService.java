package cn.yam.backmanage.service;

import cn.yam.backmanage.entity.Response.ImportUsers;
import cn.yam.backmanage.entity.Response.UserResponse;
import cn.yam.backmanage.entity.pojo.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface UserService {

    void login(String username, String password);
//
    void register(String username, String password);
//
//    UserResponse findUsers(String search, int page, int pageSize);
//
    void deleteUser(Long userId);
//
    void updateUser(Long userId, User user);
//
    void createUser(User user);

    IPage<User> getUsers(Page<User> userPage, String search);

    User findUserByUsername(String username);

    void importUsers(List<User> users);

    User findUserWithRoleByUsername(String username);
}
