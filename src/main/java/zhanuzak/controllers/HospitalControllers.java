package zhanuzak.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zhanuzak.models.Hospital;
import zhanuzak.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor

public class HospitalControllers {
    private final HospitalService hospitalService;

    @GetMapping()
    public String findAll(Model model, @RequestParam(required = false) String name,
                          @RequestParam(required = false) String address) {
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("allHospitals", hospitalService.findAll(name, address));
        return "hospitals/findAll";
    }

    @PostMapping("/save")
    String saveHospital(@ModelAttribute @Valid Hospital hospital, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "hospitals/savePage";
        }
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/create")
    String createHospital(Model model) {
        model.addAttribute("newHospital", new Hospital());
        return "hospitals/savePage";
    }


    @GetMapping("/update/{hospitalId}")
    String updatePage(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("currentHospital", hospitalService.findById(hospitalId));
        return "hospitals/updatePage";
    }

    @PostMapping("/edit/{hospitalId}")
    String editHospital(@ModelAttribute @Valid Hospital newHospital, BindingResult bindingResult,
                        @PathVariable Long hospitalId) {
        if (bindingResult.hasErrors())
            return "hospitals/updatePage";
        hospitalService.update(hospitalId, newHospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/delete/{hospitalId}")
    String deleteHospital(@PathVariable Long hospitalId) {
        hospitalService.deleteById(hospitalId);
        return "redirect:/hospitals";
    }

    @GetMapping("/department/{hospitalId}")
    String department(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("departmentEnter", hospitalService.findById(hospitalId));
        model.addAttribute(hospitalId);
        return "hospitals/findAll";
    }
}
