package cn.yam.backmanage.service;

import cn.yam.backmanage.entity.Response.UserResponse;

public interface UserService {
    void login(String username, String password);

    void register(String username, String password);

    UserResponse findUsers(String search, int page, int pageSize);
}
