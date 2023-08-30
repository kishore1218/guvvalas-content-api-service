package com.guvvalas.api.service;

import com.guvvalas.api.model.Course;
import com.guvvalas.api.repository.CourseRepository;
import com.guvvalas.api.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 */
@Slf4j
@Service
public class CourseService implements ICourseService {

    @Autowired
    public CourseRepository courseRepository;

    /**
     * @return
     */
    @Override
    public List<Course> getCourses() {
        log.info("getCourses-->");
        return courseRepository.getCourses();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Course getCourse(Integer id) {
        return courseRepository.getCourse(id);
    }

    /**
     * @param course
     */
    @Override
    public void saveCourse(Course course) {
        courseRepository.saveCourse(course);
    }

    /**
     * @param course
     */
    @Override
    public void updateCourse(Course course) {
        courseRepository.updateCourse(course);
    }
}
