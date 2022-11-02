package edu.miu.eaplayground.Lab2Rest.controller;

import edu.miu.eaplayground.Lab2Rest.entity.Course;
import edu.miu.eaplayground.Lab2Rest.entity.Student;
import edu.miu.eaplayground.Lab2Rest.service.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phase1/students")
public class StudentController {
    @Autowired
    private GenService<Student> studentGenService;

    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(studentGenService.findAll());
    }

    @GetMapping("/{id}")
    public Optional<Student> findById(@PathVariable Integer id){
        return studentGenService.find(id);
    }

    @PostMapping
    public ResponseEntity<Student> createCourse(@RequestBody Student student){
        studentGenService.save(student);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        studentGenService.delete(id);
    }

}
