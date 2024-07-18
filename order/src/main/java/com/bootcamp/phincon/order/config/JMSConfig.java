package com.bootcamp.phincon.order.config;
import com.bootcamp.phincon.order.model.Orders;
import jakarta.jms.ConnectionFactory;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.*;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.List;

@Configuration
public class JMSConfig {
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;



    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory factory=new DefaultJmsListenerContainerFactory();
        configurer.configure(factory,connectionFactory);
        return  factory;
    }

    @Bean
    public MessageConverter jsonConverter(){
        MappingJackson2MessageConverter converter=new MappingJackson2MessageConverter();
        Map<String,Class<?>> typeIdMapping=new HashMap<>();
        typeIdMapping.put("JMS_TYPE_ORDER", Orders.class);
        converter.setTypeIdMappings(typeIdMapping);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public ActiveMQConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setTrustedPackages(List.of("com.bootcamp.phincon.order"));
        return connectionFactory;


    }
}
