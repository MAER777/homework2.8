package pro.sky.homework.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework.person.Employee;
import pro.sky.homework.service.DepartmentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String allEmployee() {
        return departmentService.allEmp();
    }

    @GetMapping(path = "max-salary")
    public Employee getMaxSalaryDep(@RequestParam("department") Integer department) {
        return departmentService.getMaxSalaryDep(department);
    }
    @GetMapping(path = "min-salary")
    public Employee getMinSalaryDep(@RequestParam("department") Integer department) {
        return departmentService.getMinSalaryDep(department);
    }

    @GetMapping(path = "all")
    public List<Employee> getEmployeeDepart(@RequestParam(value = "department", required = false) Integer department) {
        if (department == null) {
            return departmentService.showAll();
        }
        return departmentService.showDepart(department);
    }
}
