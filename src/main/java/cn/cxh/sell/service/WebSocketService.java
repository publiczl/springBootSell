package cn.cxh.sell.service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocketService {

    private Session session;
    private static CopyOnWriteArraySet<WebSocketService> webSocketSet =new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSocketSet.add(this);
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("前端发送过来的信息："+message);
    }


    //发送信息
    public void sendMessage(String message){
        for(WebSocketService webSocketService:webSocketSet){
            try {
                webSocketService.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
