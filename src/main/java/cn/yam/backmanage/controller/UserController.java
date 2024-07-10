package cn.yam.backmanage.controller;

import cn.yam.backmanage.entity.Response.UserResponse;
import cn.yam.backmanage.entity.pojo.User;
import cn.yam.backmanage.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 * 日期：2024/5/24 上午8:29
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //    @PostMapping("/login")
//    ResponseEntity<Map<String, Integer>> login(@RequestBody User user) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//        Map<String, Integer> response = new HashMap<>();
//        response.put("code", 200);
//        userService.login(username, password);
//        System.out.println(ResponseEntity.ok(response));
//        // 单独查找一个username为username的用户
//        User user1 = userService.findUserByUsername(username);
//        return ResponseEntity.ok(response);
//
//    }
    @PostMapping("/login")
    ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);

        userService.login(username, password);
        System.out.println(ResponseEntity.ok(response));
        User userTemp = userService.findUserByUsername(username);
        response.put("user", userTemp);
        System.out.println(ResponseEntity.ok(response));
        return ResponseEntity.ok(response);

    }


    @PostMapping("/register")
    ResponseEntity<Map<String, Integer>> register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String, Integer> response = new HashMap<>();
        userService.register(username, password);
        response.put("code", 200);
        return ResponseEntity.ok(response);
    }

    /**
     * 获取用户列表
     *
     * @param search
     * @param page
     * @param size
     * @return
     */


    @GetMapping("/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<User> userPage = new Page<>(page, size);
        IPage<User> users = userService.getUsers(userPage, search);
        return ResponseEntity.ok().body(Map.of(
                "users", users.getRecords(),
                "total", users.getTotal()
        ));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Map<String, Integer>> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        Map<String, Integer> response = new HashMap<>();
        response.put("code", 200);
        return ResponseEntity.ok(response);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("/users/{userId}")
    public ResponseEntity<Map<String, Integer>> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        Map<String, Integer> response = new HashMap<>();
        response.put("code", 200);
        return ResponseEntity.ok(response);
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<Map<String, Integer>> createUser(@RequestBody User user) {
        userService.createUser(user);
        Map<String, Integer> response = new HashMap<>();
        response.put("code", 200);
        return ResponseEntity.ok(response);
    }

//    public ResponseEntity<?> getUserInfo()


}