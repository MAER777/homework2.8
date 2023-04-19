package pro.sky.homework.dz28;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
//public class EmployeeService implements EmployeeServiceInterface {
public class EmployeeService implements EmployeeServiceInt {
    private static final int Standart = 20;
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Трапка Траповна", 10, 50000)
    ));
    @Override
    public String allEmployee() {
        return "Сотрудников в штате: ";
    }

    @Override
    public Employee addEmployee(String fullNameEmployee, int department, double salary) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException{
        Employee employee = new Employee(fullNameEmployee, department, salary);
        if (employees.size() >= Standart) {
            return employee;
        }
        if (employees.contains(employee)) {
            return employee;
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee delEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException {
        int index = employees.indexOf(new Employee(fullNameEmployee,department,salary));
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(index);
    }

    @Override
    public Employee infoEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException {
        int index = employees.indexOf(new Employee(fullNameEmployee, department, salary));
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(index);
    }

    @Override
    public Employee getMaxSalaryDep(int dep) {
        return employees.stream()
                .filter(e -> e.getDepartment() == dep)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalAccessError("Нет такого департамента"));
    }
    @Override
    public Employee getMinSalaryDep(int dep) {
        return employees.stream()
                .filter(e -> e.getDepartment() == dep)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalAccessError("Нет такого департамента"));
    }
    @Override
    public List<Employee> showAll() {
        return employees.stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());
    }
    @Override
    public List<Employee> showDepart(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }
}
