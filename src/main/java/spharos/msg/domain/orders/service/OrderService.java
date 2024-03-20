package spharos.msg.domain.orders.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spharos.msg.domain.orders.dto.OrderRequest;
import spharos.msg.domain.orders.entity.Orders;
import spharos.msg.domain.orders.repository.OrderRepository;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.code.status.ErrorStatus;
import spharos.msg.global.api.exception.OrderException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public Orders saveOrder(List<OrderRequest.OrderDto> orderDtos, String uuid) {
        //TODO : Orders 객체 생성
    }

    private Users tryGetUser(String uuid) {
        return usersRepository
            .findByUuid(uuid)
            .orElseThrow(
                () -> new OrderException(ErrorStatus.INVALID_ORDER));
    }
}
