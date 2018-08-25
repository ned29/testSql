package com.controller;

import com.model.Department;
import com.model.Employee;
import com.model.SearchVar;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WebController {

    private long id;
    private List<Employee> employees;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "redirect:/employees";
    }

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public String getEmployees(@PageableDefault(size = 4) Pageable pageable, Model model) {
        model.addAttribute("page", employeeService.findAll(pageable));
        model.addAttribute("nameToSearch", new SearchVar());
        return "index";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable long id) {
        employeeService.remove(id);
        return "redirect:/employees";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable long id, Model model) {
        this.id = id;
        Employee employee = employeeService.findByID(id);
        model.addAttribute("employee", employee);
        return "edit";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute Employee employee) {
        List<Department> departments = departmentService.findAll();
        for (Department department : departments) {
            if (employee.getDepartment().getName().equals(department.getName())) {
                employeeService.update(employee.getActive(), employee.getName(), department.getId(), id);
                return "redirect:/employees";
            }
        }
        return "Error: failure to process department name";
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("employees", employees);
        return "search";
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute SearchVar name, Model model) {
        employees = employeeService.search(name.getParam());
        return "redirect:/search";
    }
}