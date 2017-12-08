package com.fabrice.intro.repositories;

import com.fabrice.intro.models.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {

     List<Course> findByTopicId(String topicId);
}
