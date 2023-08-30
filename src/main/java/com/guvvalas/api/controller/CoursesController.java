package com.guvvalas.api.controller;

import com.guvvalas.api.model.Chapter;
import com.guvvalas.api.model.Course;
import com.guvvalas.api.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
public class CoursesController {

    @Autowired
    private ICourseService courseService;

    /**
     *
     * @return
     */
    @GetMapping("/courses")
    public ResponseEntity<Mono<List<Course>>> getCourses(){
        log.info("getCourses...");
        var res = Mono.fromCallable(() -> {
            var courses=courseService.getCourses();
            log.info("courses ---{}",courses);
            return courses;
        }).onErrorResume(ex -> {
            return Mono.error(ex);
        });
        return ResponseEntity.ok(res);
    }


    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/course/{id}")
    public ResponseEntity<Mono<Course>> getCourse(@PathVariable("id") Integer id){
        log.info("getCourses...");
        var res = Mono.fromCallable(() -> {
            return courseService.getCourse(id);
        }).onErrorResume(ex -> {
            return Mono.error(ex);
        });
        return ResponseEntity.ok(res);
    }

    /**
     *
     * @param course
     * @return
     */
    @PostMapping("/course")
    public ResponseEntity<Mono<Void>> course(@RequestBody Course course){
        if(course.getCourseId()==null){
            courseService.saveCourse(course);
        }else{
            courseService.updateCourse(course);
        }
        return  ResponseEntity.ok(Mono.empty());
    }

}
