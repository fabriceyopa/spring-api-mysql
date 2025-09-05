/*
* MIT License
* Copyright (c) 2025 Fabrice Yopa
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT
* WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
* WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
* LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
* CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
* SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package com.fabrice.intro.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fabrice.intro.AbstractPostgresIntegrationTest;
import com.fabrice.intro.models.Topic;

/**
 * Integration tests for TopicRepository. Extends AbstractPostgresIntegrationTest to use the shared PostgreSQL
 * container.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryIntegrationTest extends AbstractPostgresIntegrationTest {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    void shouldSaveAndFindTopic() {
        // Given
        Topic topic = new Topic("Spring Boot", "Learn Spring Boot framework");

        // When
        Topic savedTopic = topicRepository.save(topic);
        Optional<Topic> foundTopic = topicRepository.findById(savedTopic.getId());

        // Then
        assertThat(savedTopic).isNotNull();
        assertThat(savedTopic.getName()).isEqualTo("Spring Boot");
        assertThat(savedTopic.getDescription()).isEqualTo("Learn Spring Boot framework");

        assertThat(foundTopic).isPresent();
        assertThat(foundTopic.get().getName()).isEqualTo("Spring Boot");
    }

    @Test
    void shouldDeleteTopic() {
        // Given
        Topic topic = new Topic("Java", "Learn Java programming");
        topicRepository.save(topic);

        // When
        topicRepository.deleteById(2);
        Optional<Topic> foundTopic = topicRepository.findById(2);

        // Then
        assertThat(foundTopic).isEmpty();
    }

    @Test
    void shouldFindAllTopics() {
        // Given
        Topic topic1 = new Topic("Docker", "Learn Docker containerization");
        Topic topic2 = new Topic("Kubernetes", "Learn Kubernetes orchestration");
        topicRepository.save(topic1);
        topicRepository.save(topic2);

        // When
        Iterable<Topic> topics = topicRepository.findAll();

        // Then
        assertThat(topics).hasSize(2);
    }
}
