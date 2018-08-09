package com.jack.mybatisplus_study1.service.impl;

import com.jack.mybatisplus_study1.entity.Student;
import com.jack.mybatisplus_study1.mapper.StudentMapper;
import com.jack.mybatisplus_study1.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jack
 * @since 2018-08-04
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
