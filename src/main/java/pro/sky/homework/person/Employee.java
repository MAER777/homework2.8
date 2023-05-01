package pro.sky.homework.person;

import java.util.Objects;

public class Employee {
    private String fullNameEmployee;

    private int department;

    private double salary;

    public Employee(String fullNameEmployee, int department, double salary) {
        this.fullNameEmployee = fullNameEmployee;
        this.department = department;
        this.salary = salary;
    }

    public String getFullNameEmployee() {
        return fullNameEmployee;
    }

    public void setFullNameEmployee(String fullNameEmployee) {
        this.fullNameEmployee = fullNameEmployee;
    }
    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullNameEmployee='" + fullNameEmployee + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(employee.salary, salary) == 0 && Objects.equals(fullNameEmployee, employee.fullNameEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullNameEmployee, department, salary);
    }
}
