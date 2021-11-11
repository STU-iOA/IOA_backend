package com.example.generator.services;

import com.example.generator.entity.Department;
import com.example.generator.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired(required = false)
    public DepartmentMapper departmentMapper;

    public Department getDepartment(Long departmentId){
        return departmentMapper.selectById(departmentId);
    }
}
