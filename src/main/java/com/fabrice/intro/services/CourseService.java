package com.fabrice.intro.services;

import com.fabrice.intro.models.Course;
import com.fabrice.intro.repositories.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> getAllCourses(Integer topicId) {
    List<Course> courses = new ArrayList<>();
    courseRepository.findByTopicId(topicId).forEach(courses::add);
    return courses;
  }

  public Course getCourse(Integer id) {
    return courseRepository.findById(id).orElse(null);
  }

  public void addCourse(Course topic) {
    courseRepository.save(topic);
  }

  public void updateCourse(Course topic) {
    courseRepository.save(topic);
  }

  public void deleteCourse(Integer id) {
    courseRepository.deleteById(id);
  }
}
