package edu.miu.eaplayground.Lab2Rest.controller;

import edu.miu.eaplayground.Lab2Rest.entity.Course;
import edu.miu.eaplayground.Lab2Rest.service.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phase1/courses")
public class CourseController {
    @Autowired
    private GenService<Course> courseGenService;

    @GetMapping
    public ResponseEntity<List<Course>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(courseGenService.findAll());
    }

    @GetMapping("/{id}")
    public Optional<Course> findById(@PathVariable int id){
        var course = courseGenService.find(id);
        return course;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        courseGenService.save(course);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        courseGenService.delete(id);
    }

}
