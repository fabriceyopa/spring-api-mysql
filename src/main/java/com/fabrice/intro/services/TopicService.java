package com.fabrice.intro.services;

import com.fabrice.intro.models.Topic;
import com.fabrice.intro.repositories.TopicRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

  @Autowired private TopicRepository topicRepository;

  public List<Topic> getAllTopics() {
    List<Topic> topics = new ArrayList<>();
    topicRepository.findAll().forEach(topics::add);
    return topics;
  }

  public Topic getTopic(Integer id) {
    return topicRepository.findById(id).orElse(null);
  }

  public void addTopic(Topic topic) {
    topicRepository.save(topic);
  }

  public void updateTopic(Topic topic) {
    topicRepository.save(topic);
  }

  public void deleteTopic(Integer id) {
    topicRepository.deleteById(id);
  }
}
