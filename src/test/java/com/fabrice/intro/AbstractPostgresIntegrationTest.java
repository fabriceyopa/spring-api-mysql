package com.fabrice.intro;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Abstract base class for integration tests that require a PostgreSQL database. This class provides
 * a shared PostgreSQL container that is reused across all test classes that extend it, following
 * best practices for TestContainers usage.
 *
 * <p>The container is started once per test class and shared among all test methods within that
 * class. The @ServiceConnection annotation automatically configures Spring Boot to use this
 * container for database connections.
 */
@Testcontainers
@ActiveProfiles("test")
public abstract class AbstractPostgresIntegrationTest {

  /**
   * Shared PostgreSQL container configuration. Using postgres:16-alpine for lightweight and fast
   * startup. The container is static to ensure it's shared across all test instances.
   */
  @Container @ServiceConnection
  protected static final PostgreSQLContainer<?> postgres =
      new PostgreSQLContainer<>("postgres:16-alpine")
          .withDatabaseName("testdb")
          .withUsername("test")
          .withPassword("test")
          .withReuse(true); // Enable container reuse for faster test execution

  /**
   * Utility method to check if the PostgreSQL container is running. Useful for debugging test setup
   * issues.
   *
   * @return true if the container is running, false otherwise
   */
  protected static boolean isPostgresRunning() {
    return postgres.isRunning();
  }
}
