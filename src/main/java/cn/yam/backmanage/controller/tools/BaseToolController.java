package cn.yam.backmanage.controller.tools;

import cn.yam.backmanage.entity.tools.paths;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 * 日期：2024/5/27 下午2:03
 */
@RestController
public class BaseToolController {
    @PostMapping("/computerelativepath")
    public ResponseEntity<Map<String, String>>  computerelativepath(@RequestBody paths pathsInput) {
        //两个绝对路径
        File absolutePath1 = new File(pathsInput.getPath());
        System.out.println("绝对路径1: " + absolutePath1);
        File absolutePath2 = new File(pathsInput.getOtherPath());
        System.out.println("绝对路径2: " + absolutePath2);

        //将绝对路径转换为URI
        URI path1 = absolutePath1.toURI();
        URI path2 = absolutePath2.toURI();

        //从两个路径创建相对路径
        URI relativePath = path2.relativize(path1);
        URI otherRelativePath = path1.relativize(path2);

        //将URI转换为字符串
        String result = relativePath.getPath();
        String otherResult = relativePath.getPath();
        // 创建一个Map对象来存储结果
        Map<String, String> response = new HashMap<>();
        response.put("relativePath", result);
        response.put("otherRelativePath", otherResult);
        // 返回ResponseEntity，包含Map和HttpStatus
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}