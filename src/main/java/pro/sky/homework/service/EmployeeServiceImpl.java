package pro.sky.homework.service;

import org.springframework.stereotype.Service;
import pro.sky.homework.exception.EmployeeAlreadyAddedException;
import pro.sky.homework.exception.EmployeeNotFoundException;
import pro.sky.homework.exception.EmployeeStorageIsFullException;
import pro.sky.homework.person.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service

public class EmployeeServiceImpl implements EmployeeService{
    private static final int Standart = 20;
    private final ValidatorService validatorService;
    List<Employee> employees = new ArrayList<>(List.of());

    public EmployeeServiceImpl(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    @Override
    public String allEmployee() {
        return "Сотрудников в штате: " + employees.size();
    }

    @Override
    public Employee addEmployee(String name,
                                String surName,
                                int department,
                                double salary) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(
                validatorService.validateName(name),
                validatorService.validateSurName(surName),
                department,
                salary);
        if (employees.size() >= Standart) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee delEmployee(String name,
                                String surName,
                                int department,
                                double salary) throws EmployeeNotFoundException {
        int index = employees.indexOf(new Employee(
                name,
                surName ,
                department,
                salary));
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(index);
    }

    @Override
    public Employee infoEmployee(String name,
                                 String surName,
                                 int department,
                                 double salary) throws EmployeeNotFoundException {
        int index = employees.indexOf(new Employee(
                name,
                surName,
                department,
                salary));
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(index);
    }
}
