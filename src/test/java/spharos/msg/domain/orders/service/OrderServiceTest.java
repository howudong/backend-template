package spharos.msg.domain.orders.service;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import spharos.msg.domain.orders.dto.OrderRequest.OrderDto;
import spharos.msg.domain.orders.entity.Orders;
import spharos.msg.domain.orders.repository.OrderRepository;
import spharos.msg.domain.users.entity.Address;
import spharos.msg.domain.users.entity.Users;
import spharos.msg.domain.users.repository.AddressRepository;
import spharos.msg.domain.users.repository.UsersRepository;
import spharos.msg.global.api.exception.OrderException;

@DataJpaTest
@Import(OrderService.class)
class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderService orderService;

    @BeforeEach
    public void init() {
        orderRepository.save(
            Orders.builder()
                .address("abc")
                .buyerId(1L)
                .buyerName("test")
                .buyerPhoneNumber("01012345667")
                .totalAmount(10L)
                .build());

        Address address = Address.builder()
            .addressName("부산 남구")
            .mobileNumber("01092312316")
            .recipient("홍준표")
            .addressName("부산 남구 용소로")
            .addressPhoneNumber("01092312316")
            .address("부산남구용")
            .build();
        addressRepository.save(
            address
        );
        usersRepository.save(
            Users.builder()
                .userName("test")
                .addresses(List.of(address))
                .email("tjdvy963@naver.com")
                .loginId("abcdsd")
                .uuid("uuid")
                .baseAddressId(0L)
                .password("1234")
                .phoneNumber("01092312316")
                .build()
        );
    }

    @Test
    @DisplayName("uuid를 찾을 수 없다면 OrderException이 발생한다.")
    void 유저_예외_발생_테스트() {
        //given
        OrderDto orderDto = new OrderDto();
        //when
        Assertions.assertThatThrownBy(
                () -> orderService.saveOrder(List.of(orderDto), "s"))
            .isInstanceOf(OrderException.class);
        //then
    }

    @Test
    @DisplayName("주소를 찾을 수 없다면 OrderException이 발생한다.")
    void 주소_예외_발생_테스트() {
        //given
        OrderDto orderDto = new OrderDto();
        //when
        Assertions.assertThatThrownBy(
                () -> orderService.saveOrder(List.of(orderDto), "uuid"))
            .isInstanceOf(OrderException.class);
        //then
    }

    @Test
    @DisplayName("주소를 찾을 수 없다면 OrderException이 발생한다.")
    void 정상_생성_테스트() {

    }

}