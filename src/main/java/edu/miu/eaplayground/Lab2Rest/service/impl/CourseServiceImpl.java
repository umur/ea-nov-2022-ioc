package edu.miu.eaplayground.Lab2Rest.service.impl;

import edu.miu.eaplayground.Lab2Rest.entity.Course;
import edu.miu.eaplayground.Lab2Rest.repository.CourseRepo;
import edu.miu.eaplayground.Lab2Rest.service.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements GenService<Course> {
    @Autowired
    private CourseRepo courseRepo;


    @Override
    public List<Course> findAll() {
        return courseRepo.findAllCourses();
    }

    @Override
    public Optional<Course> find(int id) {
        return courseRepo.findCourseById(id);
    }

    @Override
    public Course save(Course item) {
        return courseRepo.save(item);
    }

    @Override
    public void delete(int id) {
        courseRepo.delete(id);
    }
}
