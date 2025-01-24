package net.stompchat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String sender;
    private String content;
    private MessageType type;
    private final String ENTER_MESSAGE = "님이 입장하셨습니다.";
    private final String EXIT_MESSAGE = "님이 퇴장하셨습니다.";
    public void enter(){
        this.content = sender + ENTER_MESSAGE;
        this.type = MessageType.ENTER;
    }
    public void exit(){
        this.content = sender + EXIT_MESSAGE;
        this.type = MessageType.EXIT;
    }
}
