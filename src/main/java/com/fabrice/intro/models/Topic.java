package com.fabrice.intro.models;

import jakarta.persistence.*;

@Entity(name = "topic")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  public Topic() {}

  public Topic(Integer id) {
    this.id = id;
    this.name = "";
    this.description = "";
  }

  public Topic(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Topic(Integer id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
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
}
