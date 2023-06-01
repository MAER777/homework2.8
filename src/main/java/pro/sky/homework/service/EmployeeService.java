package pro.sky.homework.service;
import pro.sky.homework.exception.EmployeeAlreadyAddedException;
import pro.sky.homework.exception.EmployeeNotFoundException;
import pro.sky.homework.exception.EmployeeStorageIsFullException;
import pro.sky.homework.person.Employee;

import java.util.List;

public interface EmployeeService {
    String allEmployee();

    Employee addEmployee(String name, String surName, int department, double salary) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    Employee delEmployee(String name, String surName, int department, double salary) throws EmployeeNotFoundException;

    Employee infoEmployee(String name, String surName, int department, double salary) throws EmployeeNotFoundException;

    List<Employee> getEmployees();
}
