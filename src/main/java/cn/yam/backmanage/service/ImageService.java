package cn.yam.backmanage.service;

/**
 * 功能：
 * 日期：2024/6/3 上午12:45
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {

    @Value("${draw.api.url}")
    private String drawApiUrl;

    @Value("${draw.api.token}")
    private String drawApiToken;

    public String fetchAndConvertImage(String content) throws Exception {
        String apiUrl = drawApiUrl;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl);
        String json = createJson(content);

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "Bearer none");
        httpPost.setHeader("Content-Type", "application/json");

        String responseString = EntityUtils.toString(client.execute(httpPost).getEntity(), "UTF-8");
        JSONObject jsonObject = new JSONObject(responseString);
        String imageUrl = jsonObject.getJSONArray("choices").getJSONObject(0).getJSONObject("delta").getString("content");

        // Extract URL from markdown format ![Image](url)
        imageUrl = imageUrl.substring(imageUrl.indexOf('(') + 1, imageUrl.indexOf(')'));

        // Download image
        URL url = new URL(imageUrl);
        InputStream in = url.openStream();
        Files.copy(in, Paths.get("downloaded_image.png"), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        in.close();

        // Convert image to Base64
        byte[] fileContent = Files.readAllBytes(Paths.get("downloaded_image.png"));
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public String createJson(String content) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("model", "dalle-3");
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", content);
        jsonMap.put("messages", new Map[]{message});
        jsonMap.put("stream", false);

        try {
            return mapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
