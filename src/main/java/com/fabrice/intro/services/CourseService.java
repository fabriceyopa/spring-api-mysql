package com.fabrice.intro.services;

import com.fabrice.intro.models.Course;
import com.fabrice.intro.repositories.CourseRepository;
import com.fabrice.intro.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    public List<Course> getAllCourses(String topicId) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId).forEach(courses::add);
        return courses;
    }

    public Course getCourse(String id){
        //return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        return courseRepository.findOne(id);
    }

    public void addCourse(Course topic){
        courseRepository.save(topic);
    }

    public void updateCourse(Course topic){
        courseRepository.save(topic);
    }

    public void deleteCourse(String id){
        courseRepository.delete(id);
    }


}
