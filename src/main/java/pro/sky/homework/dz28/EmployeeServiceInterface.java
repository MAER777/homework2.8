package pro.sky.homework.dz28;

public interface EmployeeServiceInterface {
    String allEmployee();

    Employee addEmployee(String fullNameEmployee, int department, double salary);

    Employee delEmployee(String fullNameEmployee, int department, double salary);

    Employee infoEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException;
}
