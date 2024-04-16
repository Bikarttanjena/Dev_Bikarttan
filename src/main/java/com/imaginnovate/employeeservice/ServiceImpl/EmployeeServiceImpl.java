package com.imaginnovate.employeeservice.ServiceImpl;

import com.imaginnovate.employeeservice.Dto.EmployeeRequestDto;
import com.imaginnovate.employeeservice.Dto.EmployeeResponse;
import com.imaginnovate.employeeservice.Repository.EmployeeRepository;
import com.imaginnovate.employeeservice.Util.TaxcalculateUtil;
import com.imaginnovate.employeeservice.entity.Employee;
import com.imaginnovate.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public Employee saveEmployee(EmployeeRequestDto empReqDto) {

        Employee employee=DtoTOEmpConversion(empReqDto);
        return empRepo.save(employee);
    }


    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> empList=empRepo.findAll();


        return empToDtoConversion(empList);
    }

    private List<EmployeeResponse> empToDtoConversion(List<Employee> listEmp){

        List<EmployeeResponse> listEmpRes=new ArrayList<EmployeeResponse>();

        for(Employee employee:listEmp) {

            EmployeeResponse empRes=new EmployeeResponse();
            empRes.setEmployeeCode(employee.getEmployeeId());
            empRes.setFirstName(employee.getFirstName());
            empRes.setLastName(employee.getLastName());
            double yearlySal=TaxcalculateUtil.calculateAnnualSalary(employee);
            empRes.setYearlySalary(yearlySal);
            empRes.setTaxAmount(TaxcalculateUtil.calculateTaxAmount(yearlySal));
            empRes.setCessAmount(TaxcalculateUtil.calculateCessAmount(yearlySal));

            listEmpRes.add(empRes);

        }


        return listEmpRes;

    }


    private Employee DtoTOEmpConversion(EmployeeRequestDto empReqDto){

        Employee emp=new Employee();
        emp.setEmployeeId(empReqDto.getEmployeeId());
        emp.setFirstName(empReqDto.getFirstName());
        emp.setLastName(empReqDto.getLastName());
        emp.setEmail(empReqDto.getEmail());
        emp.setPhoneNumbers(empReqDto.getPhoneNumbers());
        emp.setDoj(empReqDto.getDoj());
        emp.setSalary(empReqDto.getSalary());

        return emp;



    }}
