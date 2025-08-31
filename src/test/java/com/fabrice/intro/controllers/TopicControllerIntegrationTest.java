package com.fabrice.intro.controllers;

import com.fabrice.intro.models.Topic;
import com.fabrice.intro.repositories.TopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureWebMvc
@Transactional
public class TopicControllerIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        topicRepository.deleteAll();
    }

    @Test
    public void shouldGetAllTopics() throws Exception {
        // Given
        Topic topic1 = new Topic("1", "Spring Boot", "Learn Spring Boot");
        Topic topic2 = new Topic("2", "Docker", "Learn Docker");
        topicRepository.save(topic1);
        topicRepository.save(topic2);

        // When & Then
        mockMvc.perform(get("/topics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Spring Boot"))
                .andExpect(jsonPath("$[1].name").value("Docker"));
    }

    @Test
    public void shouldGetTopicById() throws Exception {
        // Given
        Topic topic = new Topic("1", "Spring Boot", "Learn Spring Boot framework");
        topicRepository.save(topic);

        // When & Then
        mockMvc.perform(get("/topics/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Spring Boot"))
                .andExpect(jsonPath("$.description").value("Learn Spring Boot framework"));
    }

    @Test
    public void shouldCreateNewTopic() throws Exception {
        // Given
        Topic newTopic = new Topic("3", "Kubernetes", "Learn Kubernetes orchestration");

        // When & Then
        mockMvc.perform(post("/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTopic)))
                .andExpect(status().isOk());

        // Verify the topic was created
        mockMvc.perform(get("/topics/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kubernetes"));
    }

    @Test
    public void shouldUpdateTopic() throws Exception {
        // Given
        Topic existingTopic = new Topic("4", "Java", "Learn Java");
        topicRepository.save(existingTopic);

        Topic updatedTopic = new Topic("4", "Advanced Java", "Learn Advanced Java concepts");

        // When & Then
        mockMvc.perform(put("/topics/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTopic)))
                .andExpect(status().isOk());

        // Verify the topic was updated
        mockMvc.perform(get("/topics/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Advanced Java"))
                .andExpect(jsonPath("$.description").value("Learn Advanced Java concepts"));
    }

    @Test
    public void shouldDeleteTopic() throws Exception {
        // Given
        Topic topic = new Topic("5", "Python", "Learn Python programming");
        topicRepository.save(topic);

        // When & Then
        mockMvc.perform(delete("/topics/5"))
                .andExpect(status().isOk());

        // Verify the topic was deleted (should return null/empty response)
        mockMvc.perform(get("/topics/5"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}