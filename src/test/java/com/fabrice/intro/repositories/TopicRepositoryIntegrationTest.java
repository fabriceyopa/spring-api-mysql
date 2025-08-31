package com.fabrice.intro.repositories;

import com.fabrice.intro.models.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TopicRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void shouldSaveAndFindTopic() {
        // Given
        Topic topic = new Topic("1", "Spring Boot", "Learn Spring Boot framework");

        // When
        Topic savedTopic = topicRepository.save(topic);
        Optional<Topic> foundTopic = topicRepository.findById("1");

        // Then
        assertThat(savedTopic).isNotNull();
        assertThat(savedTopic.getId()).isEqualTo("1");
        assertThat(savedTopic.getName()).isEqualTo("Spring Boot");
        assertThat(savedTopic.getDescription()).isEqualTo("Learn Spring Boot framework");

        assertThat(foundTopic).isPresent();
        assertThat(foundTopic.get().getName()).isEqualTo("Spring Boot");
    }

    @Test
    public void shouldDeleteTopic() {
        // Given
        Topic topic = new Topic("2", "Java", "Learn Java programming");
        topicRepository.save(topic);

        // When
        topicRepository.deleteById("2");
        Optional<Topic> foundTopic = topicRepository.findById("2");

        // Then
        assertThat(foundTopic).isEmpty();
    }

    @Test
    public void shouldFindAllTopics() {
        // Given
        Topic topic1 = new Topic("3", "Docker", "Learn Docker containerization");
        Topic topic2 = new Topic("4", "Kubernetes", "Learn Kubernetes orchestration");
        topicRepository.save(topic1);
        topicRepository.save(topic2);

        // When
        Iterable<Topic> topics = topicRepository.findAll();

        // Then
        assertThat(topics).hasSize(2);
    }
}