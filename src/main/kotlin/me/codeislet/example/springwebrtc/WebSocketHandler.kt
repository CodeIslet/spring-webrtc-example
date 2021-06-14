package me.codeislet.example.springwebrtc

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CopyOnWriteArrayList


/**
 * @author Code Islet
 * @since 0.1.0
 */
@Component
class WebSocketHandler : TextWebSocketHandler() {

    var sessions : CopyOnWriteArrayList<WebSocketSession> = CopyOnWriteArrayList()

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        for (webSocketSession in sessions) {
            if (webSocketSession.isOpen && session.id != webSocketSession.id) {
                webSocketSession.sendMessage(message)
            }
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }
}