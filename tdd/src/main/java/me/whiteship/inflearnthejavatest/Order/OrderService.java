package me.whiteship.inflearnthejavatest.Order;

;

import me.whiteship.inflearnthejavatest.dto.ReqIdOrderDto;
import me.whiteship.inflearnthejavatest.dto.ReqOrderDto;
import me.whiteship.inflearnthejavatest.entity.OrderType;
import me.whiteship.inflearnthejavatest.entity.Orders;

import java.util.List;

public interface OrderService {


    Orders createOreder(ReqOrderDto reqOrderDto);




}
