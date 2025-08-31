package com.fabrice.intro.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {

  @Id @GeneratedValue private Integer id;
  private String name;
  private String description;
  @ManyToOne private Topic topic;

  public Course() {}

  public Course(String name, String description, Integer topicId) {
    this.name = name;
    this.description = description;
    this.topic = new Topic("", "");
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }
}
