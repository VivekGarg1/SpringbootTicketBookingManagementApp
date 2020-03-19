package com.home.SpringbootCrudWithEmbeddedDB.SpringbootEmployeeManagementApp.controller;

import com.home.SpringbootCrudWithEmbeddedDB.SpringbootEmployeeManagementApp.service.EmployeeXMLBasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeXMLBasedService employeeXMLBasedService;

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String hello(){
        return employeeXMLBasedService.getName();
    }
}
