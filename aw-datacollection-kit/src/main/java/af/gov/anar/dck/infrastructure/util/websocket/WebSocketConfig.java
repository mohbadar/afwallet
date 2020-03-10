package af.gov.anar.dck.infrastructure.util.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import af.gov.anar.dck.infrastructure.util.ParamConstant;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(ParamConstant.WEBSCOKET_ENDPOINT).setAllowedOrigins(ParamConstant.WEBSOCKET_ALLOWED_ORIGINS)
                .withSockJS();;
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(ParamConstant.NOTITICATIONS_DESTINATION_PREFIXES);
    }
}