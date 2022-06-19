package me.whiteship.inflearnthejavatest.myStudy;

import lombok.extern.slf4j.Slf4j;
import me.whiteship.inflearnthejavatest.domain.Member;
import me.whiteship.inflearnthejavatest.domain.Study;
import me.whiteship.inflearnthejavatest.domain.StudyStatus;
import me.whiteship.inflearnthejavatest.member.MemberService;
import me.whiteship.inflearnthejavatest.study.StudyRepository;
import me.whiteship.inflearnthejavatest.study.StudyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;



@ExtendWith(MockitoExtension.class)
class StudyServiceTest {


    @Mock//구현체가 없음에도 2번방법
    MemberService memberService;// extendtion필수
    @Mock StudyRepository studyRepository;
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
}