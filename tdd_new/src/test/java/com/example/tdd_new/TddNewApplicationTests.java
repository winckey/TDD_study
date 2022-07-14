package com.example.tdd_new;

import com.example.tdd_new.domain.Member;
import com.example.tdd_new.domain.Study;
import com.example.tdd_new.member.MemberService;
import com.example.tdd_new.study.StudyRepository;
import com.example.tdd_new.study.StudyService;
import com.example.tdd_new.study.StudyStatus;
import org.junit.jupiter.api.BeforeEach;
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
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


// @RunWith
//
// RunWith(SpringJUnit4ClassRunner.class)는 말 그대로 SpringJUnit4ClassRunner.class를 실행한다는 것이고, 이 클래스는 내부적으로 스프링 컨테이너를 생성해준다.


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@ContextConfiguration(initializers = TddNewApplicationTests.ContainerPropertyInitializer.class)
// @ContextConfiguration
//
// 생성된 스프링 컨테이너에 스프링 빈을 추가하기 위해서는 application-context.xml 파일과 같은 설정 파일을 읽어야 하는데, 이런 설정파일을 로드하는 어노테이션이 ContextConfiguration이다.
//
// 만약 스프링 컨테이너가 필요 없다면, 즉, 스프링 빈 팩토리에서 빈을 로드하는 것이 아닌, 직접 new로 객체를 생성해가며 테스트 코드를 작성할 것이라면 위의 어노테이션을 제거해도 된다.


class TddNewApplicationTests {



	@Mock MemberService memberService;

	@Autowired StudyRepository studyRepository;

	@Value("${container.port}") int port;

	@Container
	static DockerComposeContainer composeContainer =
			new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
					.withExposedService("study-db", 5432);

	@Test
	void createNewStudy() {
		System.out.println("========");
		System.out.println(port);

		// Given
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("keesun@email.com");

		Study study = new Study(10, "테스트");

		given(memberService.findById(1L)).willReturn(Optional.of(member));

		// When
		studyService.createNewStudy(1L, study);

		// Then
		assertEquals(1L, study.getOwnerId());
		then(memberService).should(times(1)).notify(study);
		then(memberService).shouldHaveNoMoreInteractions();
	}

	@DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
	@Test
	void openStudy() {
		// Given
		StudyService studyService = new StudyService(memberService, studyRepository);
		Study study = new Study(10, "더 자바, 테스트");
		assertNull(study.getOpenedDateTime());

		// When
		studyService.openStudy(study);

		// Then
		assertEquals(StudyStatus.OPENED, study.getStatus());
		assertNotNull(study.getOpenedDateTime());
		then(memberService).should().notify(study);
	}

	static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		//ApplicationContextInitializer를 구현하고 이를 등록함으로써, 간단한 방법으로 웹 어플리케이션의 ApplicationContext가 refresh 되기 전에 추가 처리를 할 수 있다
		//스프링실행시 가장먼저 실행되야되는경우 등록
		//https://blog.daum.net/twinsnow/164
		@Override
		public void initialize(ConfigurableApplicationContext context) {
			TestPropertyValues.of("container.port=" + composeContainer.getServicePort("study-db", 5432))
					.applyTo(context.getEnvironment());
		}
	}
}
