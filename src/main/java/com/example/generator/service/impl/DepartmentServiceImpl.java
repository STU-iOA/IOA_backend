package com.example.generator.service.impl;

import com.example.generator.entity.Department;
import com.example.generator.mapper.DepartmentMapper;
import com.example.generator.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author www
 * @since 2021-10-29
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
