package cn.yam.backmanage.service;

import cn.yam.backmanage.entity.Response.UserResponse;
import cn.yam.backmanage.entity.pojo.User;

public interface UserService {
    void login(String username, String password);

    void register(String username, String password);

    UserResponse findUsers(String search, int page, int pageSize);

    void deleteUser(int userId);

    void updateUser(int userId, User user);

    void createUser(User user);
}
