package pro.sky.homework.dz28;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
//public class EmployeeService implements EmployeeServiceInterface {
public class EmployeeService {
    private static final int Standart = 20;
    private Employee[] emp = new Employee[Standart];
    private Map<String, Employee> employeeMap = new HashMap<>();
    EmployeeService() {
        emp[0] = new Employee("Сара М", 3, 30000);
        emp[1] = new Employee("Саdра М", 3, 20000);
        emp[2] = new Employee("Саfра М", 3, 140000);
        emp[3] = new Employee("Саddра М", 2, 10000);
        emp[4] = new Employee("Сdара М", 2, 40000);
        emp[5] = new Employee("Саddра М", 1, 4000);
        emp[6] = new Employee("Саsssра М", 1, 5000);
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
        return Arrays.stream(emp)
                .filter(Objects::nonNull)
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("Dep is not"));
    }

    public Employee getMinSalaryDepart (int department) {
        return Arrays.stream(emp)
                .filter(e -> e!= null)
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("Dep is not"));
    }

    public List<Employee> showAll() {
        return Arrays.stream(emp)
                .filter(e -> e!= null)
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());
    }
    public List<Employee> showDepart(int department) {
        return Arrays.stream(emp)
                .filter(e -> e!= null)
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }
}
