package com.imaginnovate.employeeservice.RestController;

import com.imaginnovate.employeeservice.Dto.EmployeeRequestDto;
import com.imaginnovate.employeeservice.Dto.EmployeeResponse;
import com.imaginnovate.employeeservice.constants.Constants;
import com.imaginnovate.employeeservice.entity.Employee;
import com.imaginnovate.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    @Autowired
    private EmployeeService empService;

    @PostMapping("/employeeSave")
    public ResponseEntity<String> saveEmployeeData(@Valid @RequestBody EmployeeRequestDto empReqDto) {
        String status;
        Employee emp = empService.saveEmployee(empReqDto);

        if (emp != null) {

            status = Constants.SAVE_SUCESS;
        } else {

            status = Constants.SAVE_FAIL;
        }

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }


    @GetMapping("/employees")

    public ResponseEntity<?> getAllEmployees() {

        List<EmployeeResponse> listEmpRes = empService.getAllEmployees();


        if (!listEmpRes.isEmpty()) {

            return new ResponseEntity(listEmpRes, HttpStatus.OK);

        } else {

            return new ResponseEntity(Constants.DATANOT_AVAILABLE, HttpStatus.OK);

        }


    }
}

