package com.imaginnovate.employeeservice.service;

import com.imaginnovate.employeeservice.Dto.EmployeeRequestDto;
import com.imaginnovate.employeeservice.Dto.EmployeeResponse;
import com.imaginnovate.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(EmployeeRequestDto empReqDto);

    public List<EmployeeResponse> getAllEmployees();
}
