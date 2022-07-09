package me.whiteship.inflearnthejavatest.Order;


import me.whiteship.inflearnthejavatest.entity.OrderType;
import me.whiteship.inflearnthejavatest.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {


    List<Orders> findByUserId(Long userId);

    List<Orders> findByUserIdAndOrderType(Long userId , OrderType orderType);


    List<Orders> findByBoardId(Long boadId);

    List<Orders> findByBoardIdAndOrderType(Long boadId, OrderType orderType);

    void deleteByBoardId(Long boardId);
}