package spharos.msg.domain.orders.service;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import spharos.msg.domain.orders.dto.OrderRequest.OrderDto;
import spharos.msg.global.api.exception.OrderException;

@DataJpaTest
@Import(OrderService.class)
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    @DisplayName("uuid를 찾을 수 없다면 OrderException이 발생한다.")
    void 예외_발생_테스트() {
        //given
        OrderDto orderDto = new OrderDto();
        Assertions.assertThatThrownBy(
                () -> orderService.saveOrder(List.of(orderDto), "s"))
            .isInstanceOf(OrderException.class);
        //when
        //then
    }
}