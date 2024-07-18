package com.bootcamp.phincon.order.service;

import com.bootcamp.phincon.order.model.OrderDTO;
import com.bootcamp.phincon.order.model.Orders;
import com.bootcamp.phincon.order.repository.OrderActionRepository;
import org.aspectj.weaver.ast.Or;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class OrderServiceImpl implements OrderService {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    OrderActionRepository orderActionRepository;



    private static String messageSave = "queue.order";

    @Override
    public Mono<OrderDTO> save(OrderDTO orderDTO) {
        Orders orders=new Orders();
        orders.setId(orderDTO.getId());
        orders.setProductName(orderDTO.getProductName());
        orders.setPrice(orderDTO.getPrice());
        orders.setActionId(orderDTO.getActionId());
        jmsTemplate.convertAndSend(messageSave, orders);
        System.out.println("Post published: " + orders);

        return Mono.just(orderDTO);
    }
}
