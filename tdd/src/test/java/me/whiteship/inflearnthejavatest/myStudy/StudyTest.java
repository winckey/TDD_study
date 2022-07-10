package me.whiteship.inflearnthejavatest.myStudy;



import me.whiteship.inflearnthejavatest.domain.Member;
import me.whiteship.inflearnthejavatest.domain.Study;
import me.whiteship.inflearnthejavatest.study.StudyService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)//객체 생성전략 변경 한개ㅂ만 생성하도록!
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// 실행 순서를 원래는 정하면안되지만 시나리오 테스트 의도적으로 단위테스트가 아닌 여러 테스트의 연결을 할경우 사용!!!!
// order 어노테이션으로 테스트 순서 구분
// junit order 사용!
//5.5 에서는 order가 우선순위로 실행되고 나머지들이 각자 실행됨
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class StudyTest {

    @Container
    static PostgreSQLContainer postgreSQLContainer= new PostgreSQLContainer();


    @BeforeAll
    static void beforeAll(){
        postgreSQLContainer.start();
        System.out.println("---------------------");
        System.out.println(postgreSQLContainer.getJdbcUrl());
    }


    @AfterAll
    static void AfterAll(){
        postgreSQLContainer.stop();

    }

    @Test
    void stubbingTest(){


        Study study = new Study(10, "테스트");

        assertNotNull(study.getOwner());
    }
}