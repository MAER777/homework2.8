package pro.sky.homework.service;

import org.springframework.stereotype.Service;
import pro.sky.homework.exception.DepartmentNotFoundException;
import pro.sky.homework.exception.EmployeeNotFoundException;
import pro.sky.homework.person.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    public double getMaxSumSalaryDep(int dep) {
        return serviceInt.getEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public double getMaxSalaryDep(int dep) {
        return serviceInt.getEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public double getMinSalaryDep(int dep) {
        return serviceInt.getEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Map<Integer, List<Employee>> showAll() {
        return serviceInt.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> showDepart(int department) {
        return serviceInt.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }
}
