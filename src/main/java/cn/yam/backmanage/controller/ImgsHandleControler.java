package cn.yam.backmanage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

    private static final String IMAGE_DIRECTORY = "D:/B_IDEA/testE_09/backmanage/file/aidrawpic/";

    @GetMapping("/images")
    public List<String> getImages() {
        File directory = new File(IMAGE_DIRECTORY);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));

        List<String> imageUrls = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                try {
                    // Read file to byte array
                    byte[] fileContent = Files.readAllBytes(file.toPath());

                    // Encode file to Base64
                    String encodedString = Base64.getEncoder().encodeToString(fileContent);

                    // Add "data:image/png;base64," for HTML display
                    imageUrls.add("data:image/png;base64," + encodedString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageUrls;
    }

}