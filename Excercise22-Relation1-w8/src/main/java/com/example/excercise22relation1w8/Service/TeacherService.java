package com.example.excercise22relation1w8.Service;

import com.example.excercise22relation1w8.Api.ApiException;
import com.example.excercise22relation1w8.Model.Address;
import com.example.excercise22relation1w8.Model.Teacher;
import com.example.excercise22relation1w8.Repository.AddressRepository;
import com.example.excercise22relation1w8.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
    public void updateTeacher(Integer id, Teacher teacher) {
        Teacher uTeacher = teacherRepository.findTeacherById(id);
        if (uTeacher == null) {
            throw new ApiException("Teacher not found");
        }
        uTeacher.setName(teacher.getName());
        uTeacher.setEmail(teacher.getEmail());
        uTeacher.setAddress(teacher.getAddress());
        uTeacher.setAge(teacher.getAge());
        uTeacher.setSalary(teacher.getSalary());
        teacherRepository.save(uTeacher);
    }

    public void deleteTeacher(Integer id) {
        Teacher dTeacher = teacherRepository.findTeacherById(id);
        if (dTeacher == null) {
            throw new ApiException("Teacher not found");
        }
        Address address = addressRepository.findAddressById(dTeacher.getAddress().getId());
        if (address != null) {
            dTeacher.setAddress(null);
            addressRepository.delete(address);
        }
        teacherRepository.delete(dTeacher);

    }

    public Teacher teacherDetails(Integer id) {
        Teacher Teacher = teacherRepository.findTeacherById(id);
        if (Teacher == null) {
            throw new ApiException("Teacher not found");
        }
        return Teacher;
    }
}
