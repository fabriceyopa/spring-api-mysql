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
package com.fabrice.intro.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fabrice.intro.models.Topic;
import com.fabrice.intro.repositories.TopicRepository;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicService topicService;

    private Topic testTopic;

    @BeforeEach
    void setUp() {
        testTopic = new Topic(1, "Spring Boot", "Learn Spring Boot framework");
    }

    @Test
    void shouldGetAllTopics() {
        List<Topic> topics = Arrays.asList(new Topic(1, "Spring Boot", "Learn Spring Boot"),
                new Topic(2, "Docker", "Learn Docker"));
        when(topicRepository.findAll()).thenReturn(topics);

        List<Topic> result = topicService.getAllTopics();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Spring Boot");
        assertThat(result.get(1).getName()).isEqualTo("Docker");
        verify(topicRepository).findAll();
    }

    @Test
    void shouldGetTopicById() {
        when(topicRepository.findById(1)).thenReturn(Optional.of(testTopic));
        Topic result = topicService.getTopic(1);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Spring Boot");
        verify(topicRepository).findById(1);
    }

    @Test
    void shouldReturnNullWhenTopicNotFound() {
        when(topicRepository.findById(999)).thenReturn(Optional.empty());
        Topic result = topicService.getTopic(999);
        assertThat(result).isNull();
        verify(topicRepository).findById(999);
    }

    @Test
    void shouldAddTopic() {
        when(topicRepository.save(any(Topic.class))).thenReturn(testTopic);
        topicService.addTopic(testTopic);
        verify(topicRepository).save(testTopic);
    }

    @Test
    void shouldUpdateTopic() {
        when(topicRepository.save(any(Topic.class))).thenReturn(testTopic);
        topicService.updateTopic(testTopic);
        verify(topicRepository).save(testTopic);
    }

    @Test
    void shouldDeleteTopic() {
        topicService.deleteTopic(1);
        verify(topicRepository).deleteById(1);
    }
}
