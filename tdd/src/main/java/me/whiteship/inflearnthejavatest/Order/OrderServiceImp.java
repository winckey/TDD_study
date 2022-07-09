package me.whiteship.inflearnthejavatest.Order;


import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnthejavatest.dto.ItemDto;
import me.whiteship.inflearnthejavatest.dto.OptionDto;
import me.whiteship.inflearnthejavatest.dto.ReqOrderDto;
import me.whiteship.inflearnthejavatest.dto.UserDto;
import me.whiteship.inflearnthejavatest.entity.Option;
import me.whiteship.inflearnthejavatest.entity.OrderType;
import me.whiteship.inflearnthejavatest.entity.Orders;
import me.whiteship.inflearnthejavatest.entity.Prodocts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {


    private final OrderRepository orderRepository;


    @Override
    public Orders createOreder(ReqOrderDto reqOrderDto) {


        Orders orders = Orders.builder()
                .userId(reqOrderDto.getUserId())
                .boardId(reqOrderDto.getBoardId())
                .orderType(reqOrderDto.getOrderType())
                .build();

        for (ItemDto itemDto : reqOrderDto.getItemDtoList()) {
            Prodocts prodocts = Prodocts.builder()
                    .itemContent(itemDto.getItemContent())
                    .orders(orders)
                    .quantity(itemDto.getQuantity())
                    .price(itemDto.getPrice())
                    .build();


            for (OptionDto optionDto : itemDto.getOptions()) {
                Option option = Option.builder()
                        .optionContent(optionDto.getOptionContent())
                        .prodocts(prodocts)
                        .build();
            }
        }

        return orderRepository.save(orders);

    }





}
