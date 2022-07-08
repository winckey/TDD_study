package me.whiteship.inflearnthejavatest.tdd;


import me.whiteship.inflearnthejavatest.Money.Bank;
import me.whiteship.inflearnthejavatest.Money.Money;
import me.whiteship.inflearnthejavatest.domain.Study;
import me.whiteship.inflearnthejavatest.study.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//객체 생성전략 변경 한개ㅂ만 생성하도록!
public class MoneyTest {


    @Autowired
    private StudyRepository studyRepository;

    //1.서로다른 단위의 화폐더하기 환율 적용
    //2.주식수만큼 곱셈
    //3. 수량 private
    //4. dollar부작용
    //5.money 반올림
    // chf 의 연산

//
//    @Test// dollar부작용 해결
//    void test_multiplication2(){

        //달러 값변경 없이 곱셈 반환 => 값객체 리턴
        //https://livenow14.tistory.com/18 객체는한번생성되고 절대 값이 바뀌지 않음
//        Dollar five = new Dollar(5);
//
//        Dollar product = five.times(2);//일단 작성하고 에러를 해결한다 tdd 핵심!
//
//
//        assertEquals(10 , product.amount);
//        System.out.println(five.amount);
//        product = five.times(3);
//        assertEquals(15 , product.amount);
//        // amount privite으로
//        Dollar five = new Dollar(5);
//        Dollar product = five.times(2);
//        assertEquals(new Dollar(10) , product);
//        product = five.times(3);
//        assertEquals(new Dollar(15) , product);
//
//    }
//    @Test// dollar부작용 해결
//    void test_multiplication_Franc(){
//
//
//        Franc five = new Franc(5);
//        Franc product = five.times(2);
//        assertEquals(new Franc(10) , product);
//        product = five.times(3);
//        assertEquals(new Franc(15) , product);
//
//    }
    /////// 하위 클래스 제거하기
    @Test
    void test_multiplication_Money(){


        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10) , five.times(2));
        assertEquals(Money.dollar(15) , five.times(3));

        Money ten = Money.franc(10);
        assertEquals(Money.franc(20) , ten.times(2));
        assertEquals(Money.franc(30) , ten.times(3));
    }
    /////// 하위 클래스 제거를 위한 통화개념 추가
    @Test
    void test_currency(){
        assertEquals("USD" , Money.dollar(1).getCurrency());
        assertEquals("CHF" , Money.franc(1).getCurrency());
    }


    @Test// equals
    void test_equals(){

//        Dollar dollar = new Dollar(5);
//        assertEquals(new Dollar(5) , dollar);
//        assertNotEquals(new Dollar(6) , dollar);
        // dollar faranc 합치기

        assertEquals(Money.dollar(10) , Money.dollar(10));
        assertNotEquals(Money.dollar(20) , Money.dollar(10));
        assertEquals(Money.franc(10) , Money.franc(10));
        assertNotEquals(Money.franc(20) , Money.franc(10));

        // 다른 화폐비교 불가
        assertFalse(Money.franc(10).equals(Money.dollar(10)));
    }


    @Test// equals
    void test_add(){
        Money sum = Money.dollar(5).plus(Money.dollar(5));
        assertEquals(Money.dollar(5) , sum);
    }
    public void testMixedAddition(){
        Money fiveBucks = Money.dollar(5);
        Money tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }
    @Test// equals
    void test_study_repo(){
        Study study = studyRepository.save( new Study(10, "테스트"));

        assertEquals(study.getName() , "테스트");

    }
}
