package pro.sky.homework.dz28;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
//public class EmployeeService implements EmployeeServiceInterface {
public class EmployeeService {
    private static final int Standart = 20;
    private Map<String, Employee> employeeMap = new HashMap<>();
    EmployeeService() {
        employeeMap.put("a", new Employee("Сара М", 1, 3000));
        employeeMap.put("b", new Employee("Сddа М", 1, 14000));
        employeeMap.put("c", new Employee("Саdfра М", 1, 200));
        employeeMap.put("d", new Employee("Саvра М", 2, 4000));
        employeeMap.put("e", new Employee("Саrtgра М", 2, 30000));
        employeeMap.put("f", new Employee("Саbfра М", 3, 300));
        employeeMap.put("g", new Employee("Сарergа М", 3, 14000));
    }
//    List<Employee> employees = new ArrayList<>(List.of(
//            new Employee("Трапка Траповна", 10, 50000)
//    ));
//
//
//    @Override
    public String allEmployee() {
        return "Сотрудников в штате: ";
    }
//
//    @Override
//    public Employee addEmployee(String fullNameEmployee, int department, double salary) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException{
//        Employee employee = new Employee(fullNameEmployee, department, salary);
//        if (employees.size() >= Standart) {
//            return employee;
//        }
//        if (employees.contains(employee)) {
//            return employee;
//        }
//        employees.add(employee);
//        return employee;
//    }
//
//    @Override
//    public Employee delEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException {
//        int index = employees.indexOf(new Employee(fullNameEmployee,department,salary));
//        if (index == -1) {
//            throw new EmployeeNotFoundException();
//        }
//        return employees.remove(index);
//    }
//
//    @Override
//    public Employee infoEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException {
//        int index = employees.indexOf(new Employee(fullNameEmployee, department, salary));
//        if (index == -1) {
//            throw new EmployeeNotFoundException();
//        }
//        return employees.get(index);
//    }

    public Employee getMaxSalaryDepart (int department) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("Dep is not"));
    }

    public Employee getMinSalaryDepart (int department) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("Dep is not"));
    }

    public List<Employee> showAll() {
        return employeeMap.values().stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());
    }
    public List<Employee> showDepart(int department) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }
}
