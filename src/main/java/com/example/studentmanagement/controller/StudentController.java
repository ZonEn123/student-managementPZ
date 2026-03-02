package com.example.studentmanagement.controller;

import com.example.studentmanagement.common.Result;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 查询所有
    @GetMapping
    public Result<List<Student>> findAll() {
        return Result.success(studentRepository.findAll());
    }

    // 根据id查询
    @GetMapping("/{id}")
    public Result<Student> findById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(Result::success)
                .orElse(Result.fail("学生不存在"));
    }

    // 新增
    @PostMapping
    public Result<Student> create(@RequestBody Student student) {
        return Result.success(studentRepository.save(student));
    }

    // 修改
    @PutMapping("/{id}")
    public Result<Student> update(@PathVariable Long id, @RequestBody Student student) {
        if (!studentRepository.existsById(id)) {
            return Result.fail("学生不存在");
        }
        student.setId(id);
        return Result.success(studentRepository.save(student));
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return Result.fail("学生不存在");
        }
        studentRepository.deleteById(id);
        return Result.success("删除成功");
    }
}