package pro.sky.homework.service;

import org.springframework.stereotype.Service;
import pro.sky.homework.person.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl {
    private final EmployeeService serviceInt;

    public DepartmentServiceImpl(EmployeeService serviceInt) {
        this.serviceInt = serviceInt;
    }

    public String allEmp() {
        return "Сотрудников в штате: " + serviceInt.getEmployees().size();
    }
    public Employee getMaxSalaryDep(int dep) {
        return serviceInt.getEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalAccessError("Нет такого департамента"));
    }

    public Employee getMinSalaryDep(int dep) {
        return serviceInt.getEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalAccessError("Нет такого департамента"));
    }

    public List<Employee> showAll() {
        return serviceInt.getEmployees().stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    public List<Employee> showDepart(int department) {
        return serviceInt.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }
}
