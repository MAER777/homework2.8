package pro.sky.homework.dz28;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeServiceController {
    private final EmployeeService employeeService;

    public EmployeeServiceController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String allEmployee() {
        return employeeService.allEmployee();
    }

//    @GetMapping(path = "add")
//    public Employee addEmployee(@RequestParam ("fullName") String fullName,
//                                @RequestParam("department") Integer department,
//                                @RequestParam("salary") Double salary) {
//        return employeeService.addEmployee(fullName, department, salary);
//    }
//
//    @GetMapping(path = "dell")
//    public Employee dellEmployee(@RequestParam ("fullName") String fullName,
//                                 @RequestParam("department") Integer department,
//                                 @RequestParam("salary") Double salary) {
//        return employeeService.delEmployee(fullName, department, salary);
//    }
//
//    @GetMapping(path = "info")
//    public Employee infoEmployee(@RequestParam ("fullName") String fullName,
//                                 @RequestParam("department") Integer department,
//                                 @RequestParam("salary") Double salary) {
//        return employeeService.infoEmployee(fullName, department, salary);
//    }

    @GetMapping(path = "max-salary")
    public Employee getMaxSalaryDepart(@RequestParam("department") Integer department) {
        return employeeService.getMaxSalaryDepart(department);
    }
    @GetMapping(path = "min-salary")
    public Employee getMinSalaryDepart(@RequestParam("department") Integer department) {
        return employeeService.getMaxSalaryDepart(department);
    }

    @GetMapping(path = "all")
    public List<Employee> getEmployeeDepart(@RequestParam(value = "department", required = false) Integer department) {
        if (department == null) {
            return employeeService.showAll();
        }
        return employeeService.showDepart(department);
    }

}
