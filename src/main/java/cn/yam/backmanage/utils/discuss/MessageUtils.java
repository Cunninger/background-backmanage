package cn.yam.backmanage.utils.discuss;

import cn.yam.backmanage.entity.discuss.ResultMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 功能：
 * 日期：2024/6/1 下午12:15
 */
public class MessageUtils {

    public static String getMessage(boolean isSystemMessage,String fromName, Object message) {
        try {
            ResultMessage result = new ResultMessage();
            result.setSystem(isSystemMessage);
            result.setMessage(message);
            if(fromName != null) {
                result.setFromName(fromName);
            }
            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
