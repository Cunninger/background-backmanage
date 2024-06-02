package cn.yam.backmanage.component;

/**
 * 功能：
 * 日期：2024/6/1 下午4:44
 */

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author websocket服务
 */
@ServerEndpoint(value = "/imserver/{username}/{room}")
@Component
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    public static final Map<String, Set<Session>> roomMap = new ConcurrentHashMap<>();
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("room") String room) {
        if (room != null && !room.isEmpty()) {
            Set<Session> roomSessions = roomMap.getOrDefault(room, new HashSet<>());
            roomSessions.add(session);
            roomMap.put(room, roomSessions);
            log.info("User {} joined room {}. Current users in room: {}", username, room, roomSessions.size());
        }

        sessionMap.put(username, session);
        log.info("有新用户加入，username={}, 当前在线人数为：{}", username, sessionMap.size());

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.set("users", array);
        for (String key : sessionMap.keySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("username", key);
            array.add(jsonObject);
        }
        sendAllMessage(JSONUtil.toJsonStr(result));
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username, @PathParam("room") String room) {
        if (room != null && !room.isEmpty()) {
            Set<Session> roomSessions = roomMap.get(room);
            if (roomSessions != null) {
                roomSessions.remove(session);
                log.info("User {} left room {}. Current users in room: {}", username, room, roomSessions.size());
            }
        }

        sessionMap.remove(username);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, sessionMap.size());
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username, @PathParam("room") String room) {
        log.info("服务端收到用户username={}的消息:{}", username, message);
        JSONObject obj = JSONUtil.parseObj(message);
        String toUsername = obj.getStr("to");
        String text = obj.getStr("text");
        String toRoom = obj.getStr("room");

        if (toRoom != null && !toRoom.isEmpty()) {
            Set<Session> roomSessions = roomMap.get(toRoom);
            if (roomSessions != null) {
                for (Session roomSession : roomSessions) {
                    if (roomSession != session) {
                        this.sendMessage(message, roomSession);
                    }
                }
            }
        } else if (toUsername != null) {
            Session toSession = sessionMap.get(toUsername);
            if (toSession != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.set("from", username);
                jsonObject.set("text", text);
                this.sendMessage(jsonObject.toString(), toSession);
                log.info("发送给用户username={}，消息：{}", toUsername, jsonObject.toString());
            } else {
                log.info("发送失败，未找到用户username={}的session", toUsername);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
}
