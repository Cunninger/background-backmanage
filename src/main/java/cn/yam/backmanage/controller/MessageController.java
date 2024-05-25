package cn.yam.backmanage.controller;

import cn.yam.backmanage.entity.chat.saveMessage;
import cn.yam.backmanage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 * 日期：2024/5/25 下午5:44
 */
@RestController("/savechatapi")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages/save")
    public void saveMessage(@RequestBody saveMessage message) {
        messageService.saveMessage(message);
    }

    @GetMapping("/messages/{id}")
    public saveMessage getMessage(@PathVariable String id) {
        return messageService.getMessage(id);
    }
}
