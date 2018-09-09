package com.controller;

import com.model.Department;
import com.model.Employee;
import com.model.SearchVar;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WebController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class.getName());

    private long id;
    private List<Employee> employees;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("redirect:/employees");
    }

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployees(@PageableDefault(size = 4) Pageable pageable, Model model) {
        try {
            model.addAttribute("page", employeeService.findAll(pageable));
            model.addAttribute("nameToSearch", new SearchVar());
            return ResponseEntity.ok("index");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        try {
            employeeService.remove(id);
            return ResponseEntity.ok("redirect:/employees");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployee(@PathVariable long id, Model model) {
        try {
            this.id = id;
            Employee employee = employeeService.findByID(id);
            model.addAttribute("employee", employee);
            return ResponseEntity.ok("edit");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public ResponseEntity<String> updateEmployee(@ModelAttribute Employee employee) {
        try {
            List<Department> departments = departmentService.findAll();
            for (Department department : departments) {
                if (employee.getDepartment().getName().equals(department.getName())) {
                    employeeService.update(employee.getActive(), employee.getName(), department.getId(), id);
                    return ResponseEntity.ok("redirect:/employees");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.badRequest().body("Error: failure to process department name");
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ResponseEntity<String> search(Model model) {
        try {
            model.addAttribute("employees", employees);
            return ResponseEntity.ok("search");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.badRequest().body("Error");
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ResponseEntity<String> search(@ModelAttribute SearchVar name, Model model) {
        try {
            employees = employeeService.search(name.getParam());
            return ResponseEntity.ok("redirect:/search");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.badRequest().body("Error");
    }
}