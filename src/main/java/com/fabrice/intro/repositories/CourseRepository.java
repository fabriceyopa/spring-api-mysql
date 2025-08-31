package com.fabrice.intro.repositories;

import com.fabrice.intro.models.Course;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

  List<Course> findByTopicId(Integer topicId);
}
