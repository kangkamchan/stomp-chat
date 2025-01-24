package net.stompchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //web socket 연결 엔드포인트
        registry.addEndpoint("/stomp-chat")
                //CORS 이슈 해결 위한 url 설정
                //.setAllowedOrigins("http://localhost:8080")
                //url 정규식 설정도 가능
                //.setAllowedOriginPatterns("*")
                .withSockJS()
        ;
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //클라이언트가 메시지 전송을 요청할 경로, 컨트롤러의 해당 경로를 가진 @MessageMapping 메서드로 라우팅해줌
        registry.setApplicationDestinationPrefixes("/app");
        //브로커가 처리할 경로 접두사를 설정함 subscribe 와 broadcast 에 사용됨
        registry.enableSimpleBroker("/topic", "/queue");
    }
}
