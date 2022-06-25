package me.whiteship.inflearnthejavatest.tdd;


import me.whiteship.inflearnthejavatest.Money.Dollar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)//객체 생성전략 변경 한개ㅂ만 생성하도록!
public class MoneyTest {



    //1.서로다른 단위의 화폐더하기 환율 적용
    //2.주식수만큼 곱셈
    //3. 수량 private
    //4. dollar부작용
    //5.money 반올림




    @Test
    void test_multiplication(){

        Dollar five = new Dollar(5);
        five.times(2);
        assertEquals(10 , Dollar.amount);
    }







}
