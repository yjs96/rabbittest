package com.example.orderservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ConfigChangeListener {
    private static final Logger logger = LoggerFactory.getLogger(ConfigChangeListener.class);

    @Value("${my.property:default value}")
    private String myProperty;

    @EventListener(RefreshScopeRefreshedEvent.class)
    public void onRefresh(RefreshScopeRefreshedEvent event) {
        logger.info("정보: Order Service 설정 변경: {}", myProperty);
    }
}
