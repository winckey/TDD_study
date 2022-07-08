package me.whiteship.inflearnthejavatest.myStudy;



import me.whiteship.inflearnthejavatest.domain.Study;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)//객체 생성전략 변경 한개ㅂ만 생성하도록!
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// 실행 순서를 원래는 정하면안되지만 시나리오 테스트 의도적으로 단위테스트가 아닌 여러 테스트의 연결을 할경우 사용!!!!
// order 어노테이션으로 테스트 순서 구분
// junit order 사용!
//5.5 에서는 order가 우선순위로 실행되고 나머지들이 각자 실행됨
public class StudyTest {

    @Test
    @Tag("fast")
    @Order(2)
    void create_new_study(){

        Study study = null;
        assertNull(study);

    }

    @Test
    @Tag("slow")// 실행시 태그별로 구분하여 실행 가능

    void create_new_study3(){

        Study study = null;
        assertNull(study);
//        assertEquals(StudyStatus.DRAFT,study.getStatus(), "study status is not draft");

    }


    @Order(1)
    @RepeatedTest(value = 10 , name = "test")//@test 와 중복사용 불가능
    void test_repeated(RepetitionInfo repetitionInfo){// 반복시 총횟수와 현재횟수 저장객체
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());

        Study study = null;
        assertNull(study);
//        assertEquals(StudyStatus.DRAFT,study.getStatus(), "study status is not draft");

    }
    //각각의 테스트는 모두 서로 다른 객체이다!!!! 테스트의 순서를 예측할수 없기 때문에 각각의 테스트들의 의존성을 없애기 위해서!!!!
    // -> 실행환경에따라 바뀜 어디서 실행하던 같은 테스트를 할수있어야함 !!!

}