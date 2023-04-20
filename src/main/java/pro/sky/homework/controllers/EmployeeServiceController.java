package pro.sky.homework.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework.person.Employee;
import pro.sky.homework.service.EmployeeService;
import pro.sky.homework.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeServiceController {
    private final EmployeeService employeeService;

    public EmployeeServiceController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public String allEmployee() {
        return employeeService.allEmployee();
    }

    @GetMapping(path = "add")
    public Employee addEmployee(@RequestParam ("fullName") String fullName,
                                @RequestParam("department") Integer department,
                                @RequestParam("salary") Double salary) {
        return employeeService.addEmployee(fullName, department, salary);
    }

    @GetMapping(path = "dell")
    public Employee dellEmployee(@RequestParam ("fullName") String fullName,
                                 @RequestParam("department") Integer department,
                                 @RequestParam("salary") Double salary) {
        return employeeService.delEmployee(fullName, department, salary);
    }

    @GetMapping(path = "info")
    public Employee infoEmployee(@RequestParam ("fullName") String fullName,
                                 @RequestParam("department") Integer department,
                                 @RequestParam("salary") Double salary) {
        return employeeService.infoEmployee(fullName, department, salary);
    }


}
