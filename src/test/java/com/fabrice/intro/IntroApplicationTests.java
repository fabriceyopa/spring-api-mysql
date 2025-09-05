package com.fabrice.intro;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration tests for the main Spring Boot application. Extends AbstractPostgresIntegrationTest
 * to use the shared PostgreSQL container.
 */
@SpringBootTest
public class IntroApplicationTests extends AbstractPostgresIntegrationTest {

  @Test
  void postgresContainerIsRunning() {
    // Test that the PostgreSQL container is running
    assert isPostgresRunning();
  }
}
