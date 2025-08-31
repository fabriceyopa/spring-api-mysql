package com.fabrice.intro.controllers;

import com.fabrice.intro.models.Course;
import com.fabrice.intro.models.Topic;
import com.fabrice.intro.services.CourseService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping("/topics/{id}/courses")
  public List<Course> getTopics(@PathVariable Integer id) {
    return courseService.getAllCourses(id);
  }

  @GetMapping("/topics/{topicId}/courses/{id}")
  public Course getCourse(@PathVariable Integer id) {
    return courseService.getCourse(id);
  }

  @PostMapping("/topics/{topicId}/courses")
  public void addCourse(@RequestBody Course course, @PathVariable String topicId) {
    course.setTopic(new Topic("", ""));
    courseService.addCourse(course);
  }

  @PutMapping("/topics/{topicId}/courses/{id}")
  public void updateCourse(
      @RequestBody Course course, @PathVariable String topicId, @PathVariable String id) {
    course.setTopic(new Topic("", ""));
    courseService.updateCourse(course);
  }

  @DeleteMapping("/topics/{topicId}/courses/{id}")
  public void deleteCourse(@PathVariable Integer id) {
    courseService.deleteCourse(id);
  }
}
