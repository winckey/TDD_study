package me.whiteship.inflearnthejavatest.myStudy;


import me.whiteship.inflearnthejavatest.domain.Member;
import me.whiteship.inflearnthejavatest.domain.Study;

import me.whiteship.inflearnthejavatest.member.MemberService;
import me.whiteship.inflearnthejavatest.study.StudyRepository;
import me.whiteship.inflearnthejavatest.study.StudyService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;




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