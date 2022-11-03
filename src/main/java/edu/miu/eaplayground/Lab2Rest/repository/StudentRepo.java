package edu.miu.eaplayground.Lab2Rest.repository;

import edu.miu.eaplayground.Lab2Rest.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepo {
    private List<Student> students = new ArrayList<>();

    public List<Student> getAllStudents() {
        return students.stream().filter(a -> !a.isDeleted())
                .collect(Collectors.toList());
    }

    public Student save(Student student) {
        students.add(student);
        return student;
    }

    public Optional<Student> findStudentById(int id) {
        return getAllStudents().stream().filter(a -> a.getId() == id).findFirst();
    }

    public void delete(int id) {
        findStudentById(id).ifPresent(val -> val.setDeleted(true));
    }
}
