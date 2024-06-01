package cn.yam.backmanage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * 功能：
 * 日期：2024/5/26 上午10:52
 */
@RestController
public class ImgsHandleControler {

    private static final String UPLOAD_DIR = "/D:/B_IDEA/testE_09/backmanage/file/useravatar/";

    @PostMapping("/upload/avatar")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 保存文件到指定目录
            String fileName = file.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }


}