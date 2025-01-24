package net.stompchat.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.stompchat.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class StompChatMessageController {
    /**
     * 채팅방 입장 시 입장 메시지를 전송함
     * @param chatMessage 보내는 사람 정보를 포함한 채팅 메시지 객체
     * @return 보내는사람 정보와 입장 메시지를 포함한 ChatMessage 객체
     */
    @MessageMapping("/chat/enter")
    @SendTo("/topic/chat")
    public ChatMessage enter(ChatMessage chatMessage){
        log.info("enter : {}",chatMessage.getSender());
        chatMessage.enter();
        return chatMessage;
    }

    /**
     * 채팅방 퇴장 시 퇴장 메시지를 전송함
     * @param chatMessage 보내는 사람 정보를 포함한 채팅 메시지 객체
     * @return 보내는사람 정보와 퇴장 메시지를 포함한 ChatMessage 객체
     */
    @MessageMapping("/chat/exit")
    @SendTo("/topic/chat")
    public ChatMessage exit(ChatMessage chatMessage){
        chatMessage.exit();
        return chatMessage;
    }

    /**
     * 일반적인 메시지 전송
     * @param chatMessage 보내는 사람 정보와 채팅 메시지 내용을 포함한 채팅 메시지 객체
     * @return 보내는사람 정보와 채팅 메시지 내용을 포함한 ChatMessage 객체
     */
    @MessageMapping("/chat/send")
    @SendTo("/topic/chat")
    public ChatMessage send(@Payload ChatMessage chatMessage){
        log.info("send : {}",chatMessage.getSender());
        return chatMessage;
    }

    @MessageExceptionHandler
    public ChatMessage handleException(Exception e) {
        log.error(e.getMessage());
        return ChatMessage.builder()
                .sender("error")
                .content("오류가 발생했습니다.")
                .build();
    }
}
