package me.whiteship.inflearnthejavatest.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqIdOrderDto {


    private Long boardId;


    private Long orderId;


    private Long userId;
}
