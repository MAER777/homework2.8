package pro.sky.homework.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.homework.person.Employee;
import pro.sky.homework.service.DepartmentServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String allEmployee() {
        return departmentService.allEmp();
    }

    @GetMapping(path = "/{id}/salary/max")
    public double getMaxSalaryDep(@PathVariable int id) {
        return departmentService.getMaxSalaryDep(id);
    }
    @GetMapping(path = "/{id}/salary/min")
    public double getMinSalaryDep(@PathVariable int id) {
        return departmentService.getMinSalaryDep(id);
    }
    @GetMapping(path = "/{id}/salary/sum")
    public double getSumSalaryDep(@PathVariable int id) {
        return departmentService.getMaxSumSalaryDep(id);
    }


    @GetMapping(path = "/{id}/employees")
    public List<Employee> getsEmployeeDeparts(@PathVariable int id) {
        return departmentService.showDepart(id);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List <Employee>> employeesGroupDep () {
        return departmentService.showAll();
    }
}
