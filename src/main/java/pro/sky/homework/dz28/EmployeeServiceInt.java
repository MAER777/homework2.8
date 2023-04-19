package pro.sky.homework.dz28;

import java.util.List;

public interface EmployeeServiceInt {
    //
    //
    //    @Override
    String allEmployee();

    Employee addEmployee(String fullNameEmployee, int department, double salary) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    Employee delEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException;

    Employee infoEmployee(String fullNameEmployee, int department, double salary) throws EmployeeNotFoundException;

    Employee getMaxSalaryDep(int dep);

    Employee getMinSalaryDep(int dep);

    List<Employee> showAll();

    List<Employee> showDepart(int department);
}
