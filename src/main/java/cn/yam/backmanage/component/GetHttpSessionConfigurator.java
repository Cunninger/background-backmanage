package cn.yam.backmanage.component;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 功能：
 * 日期：2024/6/1 下午12:24
 */

/**
 * 这个类名为`GetHttpSessionConfigurator`，它是一个Java类，继承自`ServerEndpointConfig.Configurator`。这个类的主要作用是在WebSocket握手阶段修改握手协议。
 *
 * 在这个类中，重写了`modifyHandshake`方法。这个方法在握手阶段被调用，用于修改握手协议。在这个方法中，首先从`HandshakeRequest`对象中获取了`HttpSession`对象，然后将这个`HttpSession`对象存储到了`ServerEndpointConfig`的用户属性中。
 *
 * 这样做的目的是为了在WebSocket的服务端点中获取到`HttpSession`对象，从而可以在WebSocket的服务端点中访问到HTTP的会话信息。这对于在WebSocket服务端点中需要使用HTTP会话信息的场景非常有用，例如需要获取当前登录用户的信息等。
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}
