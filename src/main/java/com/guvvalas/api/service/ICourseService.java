package com.guvvalas.api.service;

import com.guvvalas.api.model.Course;

import java.util.List;


/**
 *
 */
public interface ICourseService {

    /**
     *
     * @return
     */
    public List<Course> getCourses();

    /**
     *
     * @param id
     * @return
     */
    public Course getCourse(Integer id);


    /**
     *
     * @param course
     */
    public void saveCourse(Course course);

    /**
     *
     * @param course
     */
    public void updateCourse(Course course);

}
