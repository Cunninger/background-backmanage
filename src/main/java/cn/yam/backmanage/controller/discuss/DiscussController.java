package cn.yam.backmanage.controller.discuss;

import cn.yam.backmanage.entity.discuss.Result;

import cn.yam.backmanage.entity.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// MessageController.java
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/discuss")
public class DiscussController {


    @PostMapping("/login")
    public Result login( @RequestBody User user, HttpSession session) {
        Result result = new Result();
        if (user != null && "123".equals(user.getPassword())) {
            result.setFlag(true);
            //将用户名存储到session对象中
            session.setAttribute("user", user.getUsername());
        } else {
            result.setFlag(false);
            result.setMessage("登陆失败");
        }

        return result;
    }


    @RequestMapping("/discuss/getUsername")
    public String getUsername(HttpSession session) {
        String username = (String) session.getAttribute("user");
        return username;
    }

}