import com.bootcamp.phincon.order.model.OrderDTO;
import com.bootcamp.phincon.order.model.Orders;
import com.bootcamp.phincon.order.repository.OrderActionRepository;
import com.bootcamp.phincon.order.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private OrderActionRepository orderActionRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void save() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setProductName("TestProduct");
        orderDTO.setPrice(100L);
        orderDTO.setActionId(2L);

        // Act
        Mono<OrderDTO> result = orderService.save(orderDTO);

        // Assert
        verify(jmsTemplate, times(1)).convertAndSend(eq("queue.order"), any(Orders.class));
        // Add more assertions based on your requirements
    }
}