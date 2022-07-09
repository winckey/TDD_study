package me.whiteship.inflearnthejavatest.Order;

import me.whiteship.inflearnthejavatest.dto.ItemDto;
import me.whiteship.inflearnthejavatest.dto.OptionDto;
import me.whiteship.inflearnthejavatest.dto.ReqOrderDto;
import me.whiteship.inflearnthejavatest.entity.OrderType;
import me.whiteship.inflearnthejavatest.entity.Orders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


//@DataJpaTest//https://webcoding-start.tistory.com/20
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//객체 생성전략 변경 한개ㅂ만 생성하도록!
@ExtendWith({MockitoExtension.class})

class OrderServiceImpTest {


    @Autowired // junit5는 di를 스스로 지원 다른방식은 junit이 먼저 생성자를 생성하려 해서 안댐
    OrderRepository orderRepository;


    @Test
    void createOreder() {
        //given
        OrderService orderService = new OrderServiceImp(orderRepository);

        OptionDto optionDto = OptionDto.builder()
                .optionContent("option")
                .build();
        ItemDto itemDto = ItemDto.builder()
                .price(1000)
                .quantity(1)
                .itemContent("item")
                .options(new ArrayList<>())
                .build();
        itemDto.getOptions().add(optionDto);
        ReqOrderDto reqOrderDto = ReqOrderDto.builder()
                .boardId(1L)
                .orderType(OrderType.ORDER_DELIVERY)
                .itemDtoList(new ArrayList<>())
                .build();
        reqOrderDto.getItemDtoList().add(itemDto);

        //when

        Orders orders = orderService.createOreder(reqOrderDto);


        //then
        assertEquals( OrderType.ORDER_DELIVERY, orders.getOrderType());
        assertEquals( 1L, orders.getBoardId());
    }

    @Test
    void a_test_임시() {
        System.out.println("test");
        assertEquals( 1L, 1L);
    }


}