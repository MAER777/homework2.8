package pro.sky.homework.service;
import pro.sky.homework.exception.EmployeeAlreadyAddedException;
import pro.sky.homework.exception.EmployeeNotFoundException;
import pro.sky.homework.exception.EmployeeStorageIsFullException;
import pro.sky.homework.person.Employee;

import java.util.List;

public interface EmployeeService {
    String allEmployee();

    Employee addEmployee(String fullNameEmployee, int department, double salary) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    Employee delEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException;

    Employee infoEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException;

    List<Employee> getEmployees();
}
