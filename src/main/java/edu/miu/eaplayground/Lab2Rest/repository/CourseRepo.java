package edu.miu.eaplayground.Lab2Rest.repository;

import edu.miu.eaplayground.Lab2Rest.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepo {
    private List<Course> courses = new ArrayList<>();

    public List<Course> findAllCourses() {
        return courses.stream().filter(a -> !a.isDeleted()).collect(Collectors.toList());
    }

    public Optional<Course> findCourseById(int id) {
        return courses.stream().filter(a -> a.getId() == id).findFirst();
    }

    public Course save(Course course) {
        courses.add(course);
        return course;
    }

    public void delete(int id) {
        courses.stream().filter(a -> a.getId() == id).findAny().ifPresent(a -> a.setDeleted(true));
    }
}
