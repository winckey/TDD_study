package me.whiteship.inflearnthejavatest.myStudy;


import me.whiteship.inflearnthejavatest.domain.Study;
import me.whiteship.inflearnthejavatest.domain.StudyStatus;
import me.whiteship.inflearnthejavatest.member.MemberService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)//객체 생성전략 변경 한개ㅂ만 생성하도록!
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// 실행 순서를 원래는 정하면안되지만 시나리오 테스트 의도적으로 단위테스트가 아닌 여러 테스트의 연결을 할경우 사용!!!!
// order 어노테이션으로 테스트 순서 구분
// junit order 사용!
//5.5 에서는 order가 우선순위로 실행되고 나머지들이 각자 실행됨
public class MokitoTest {

    @Mock//구현체가 없음에도 2번방법
    MemberService memberService;// extendtion필수
    @Mock
    StudyRepository studyRepository;

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");

        given(studyRepository.save(study)).willReturn(study);
        // TODO studyRepository Mock 객체의 save 메소드를호출 시 study를 리턴하도록 만들기.

        // When
        studyService.openStudy(study);

        // Then
        assertEquals(StudyStatus.OPENED ,study.getStatus());
        assertNotNull(study.getOpenedDateTime());

        then(memberService).should(times(1)).notify(study);
        assertNotNull(study.getOpenedDateTime());
        // TODO study의 status가 OPENED로 변경됐는지 확인
        // TODO study의 openedDataTime이 null이 아닌지 확인
        // TODO memberService의 notify(study)가 호출 됐는지 확인.

    }



}