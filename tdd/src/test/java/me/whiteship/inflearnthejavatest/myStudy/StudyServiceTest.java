package me.whiteship.inflearnthejavatest.myStudy;


import lombok.extern.slf4j.Slf4j;
import me.whiteship.inflearnthejavatest.domain.Member;
import me.whiteship.inflearnthejavatest.domain.Study;

import me.whiteship.inflearnthejavatest.member.MemberService;
import me.whiteship.inflearnthejavatest.study.StudyRepository;
import me.whiteship.inflearnthejavatest.study.StudyService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
class StudyServiceTest {


    @Mock//구현체가 없음에도 2번방법
    MemberService memberService;// extendtion필수
    @Autowired
    StudyRepository studyRepository;

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


    @Test
    void createStudyService(){

//        MemberService memberService = new MemberService() {
//            @Override
//            public Optional<Member> findById(Long memberId) {
//                return Optional.empty();
//            }
//            @Override
//            public void validate(Long memberId) {
//            }
//            @Override
//            public void notify(Study newstudy) {
//            }
//            @Override
//            public void notify(Member member) {
//            }
//        };==>1번방법
//        MemberService memberService   = mock(MemberService.class);

        StudyService studyService = new StudyService(memberService , studyRepository);
        assertNotNull(studyService);


        Member member = new Member();
        member.setId(1L);
        member.setEmail("test");

//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        //mock객체의 동작방법 Stubbing 1이외의 값은 모두 null (일종의 간의함수)
        when(memberService.findById(any())).thenReturn(Optional.of(member));
        // any() 어떤 값을 넘기던 이걸로 해결
        Member member1 = memberService.findById(1L).get();
        assertEquals("test" , member1.getEmail());


    }

    @Test
    void stubbingTest(){


        Study study = new Study(10, "테스트");
        StudyService studyService = new StudyService(memberService , studyRepository);

        assertNotNull(studyService);
// TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 Stubbing
// TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        Member test = Member.builder()
                .id(1L)
                .email("test")
                .build();
        when(memberService.findById(any())).thenReturn(Optional.ofNullable(test));
        when(studyRepository.save(study)).thenReturn(study);


        studyService.createNewStudy(1L, study);

        assertNotNull(study.getOwner());
        assertEquals( test.getId() , study.getOwner() );
    }


}