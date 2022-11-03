package edu.miu.eaplayground.Lab2Rest.service.impl;

import edu.miu.eaplayground.Lab2Rest.entity.Student;
import edu.miu.eaplayground.Lab2Rest.repository.StudentRepo;
import edu.miu.eaplayground.Lab2Rest.service.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements GenService<Student> {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> findAll() {
        return studentRepo.getAllStudents();
    }

    @Override
    public Optional<Student> find(int id) {
        return studentRepo.findStudentById(id);
    }

    @Override
    public Student save(Student item) {
        return studentRepo.save(item);
    }

    @Override
    public void delete(int id) {
        studentRepo.delete(id);
    }
}
