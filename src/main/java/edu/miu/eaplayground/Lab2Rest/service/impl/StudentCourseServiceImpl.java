package edu.miu.eaplayground.Lab2Rest.service.impl;

import edu.miu.eaplayground.Lab2Rest.entity.Course;
import edu.miu.eaplayground.Lab2Rest.entity.Student;
import edu.miu.eaplayground.Lab2Rest.service.GenService;
import edu.miu.eaplayground.Lab2Rest.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private GenService<Student> studentGenService;

    @Autowired
    private GenService<Course> courseGenService;

    @Override
    public void assignCourse(int studentId, int courseId) {
        Optional<Student> student = studentGenService.find(studentId);
        Optional<Course> course = courseGenService.find(courseId);

        if (student.isEmpty() || course.isEmpty()) {
            throw new NoSuchElementException();
        }

        student.ifPresent(s -> s.addCourse(course.get()));
    }

    @Override
    public List<Student> getStudentByMajor(String major) {
        return studentGenService.findAll().stream().filter(a -> a.getMajor().equals(major)).collect(Collectors.toList());
    }

    @Override
    public List<Course> getCourseByStudentId(int id) {
        return studentGenService.findAll().stream().filter(a -> a.getId().equals(id))
                .findAny().map(Student::getCourseTaken)
                .orElse(new ArrayList<>());
    }
}
