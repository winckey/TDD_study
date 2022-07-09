package me.whiteship.inflearnthejavatest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.whiteship.inflearnthejavatest.entity.OrderType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReqOrderDto {


    private Long boardId;


    private Long userId;


    private OrderType orderType;


    private BoardDto boardDto;


    private UserDto userDto;

    private List<ItemDto> itemDtoList;

}
