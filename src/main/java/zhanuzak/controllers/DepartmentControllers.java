package zhanuzak.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhanuzak.models.Department;
import zhanuzak.service.DepartmentService;

@Controller

@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentControllers {
    private final DepartmentService departmentService;

//    @GetMapping()
//    String findAllDepartments(Model model) {
//        model.addAttribute("departments", departmentService.findAll());
//        return "departments/findAll";
//    }

    @GetMapping("/{hospitalId}")
    String findAllDepartmentsByHospital(Model model, @PathVariable Long hospitalId) {
        model.addAttribute("allDepartments", departmentService.findAllHospitalId(hospitalId));
        model.addAttribute("hospitalId", hospitalId);
        return "departments/findDepartmentsByHospital";
    }


    @GetMapping("/create/{hospitalId}")
    String createDepartmentByHospitalId(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("newDepartment", new Department());
        return "departments/saveDepartmentByHospital";
    }

    @PostMapping("/save/{hospitalId}")
    String saveDepartmentByHospitalId(@PathVariable Long hospitalId, @ModelAttribute Department department) {
        departmentService.save(hospitalId, department);
        return "redirect:/departments/" + hospitalId;
    }

    @GetMapping("/update/{departmentId}")
    String updateDepartment(@PathVariable Long departmentId, Model model) {
        model.addAttribute("currentDepartment", departmentService.findDepartmentById(departmentId));
        return "departments/updatePage";
    }

    @PostMapping("/edit/{departmentId}")
    String editDepartment(@ModelAttribute Department newDepartment, @PathVariable Long departmentId) {
        departmentService.updateById(newDepartment, departmentId);
        return "redirect:/departments/" + departmentService.findDepartmentById(departmentId).getHospital().getId();
    }

}
