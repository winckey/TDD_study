package me.whiteship.inflearnthejavatest.tdd;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class MixedLifecycleTests {




    // will be started before and stopped after each test method
    @Container
    private static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("foo")
            .withUsername("foo")
            .withPassword("secret");

    @BeforeAll
    static void beforeAll(){
        System.out.println(postgresqlContainer.getJdbcUrl());
    }


    @Test
    void test() {

        assertTrue(postgresqlContainer.isRunning());
    }
}