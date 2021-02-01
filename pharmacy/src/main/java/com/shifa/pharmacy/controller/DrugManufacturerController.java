package com.shifa.pharmacy.controller;

import com.shifa.pharmacy.model.DrugManufacturer;
import com.shifa.pharmacy.repository.DrugManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DrugManufacturerController {

    final
    DrugManufacturerRepository drugManufacturerRepository;

    public DrugManufacturerController(DrugManufacturerRepository drugManufacturerRepository) {
        this.drugManufacturerRepository = drugManufacturerRepository;
    }

    @RequestMapping(value = "/companies/list", method = RequestMethod.GET)
    public String companies(Model model){
        model.addAttribute("companies", drugManufacturerRepository.findAll());
        return "company/list";
    }

    @RequestMapping(value = "/companies/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "company/create";
    }

    @RequestMapping(value = "/companies/add", method = RequestMethod.POST)
    public String add(Model model, @RequestParam String name, @RequestParam String address) {

        DrugManufacturer drugManufacturer = new DrugManufacturer(name, address);
        drugManufacturerRepository.save(drugManufacturer);
        return "redirect:/companies/list";
    }

    @RequestMapping(value = "/companies/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("company", drugManufacturerRepository.findById(id).get());
        return "company/edit";
    }


    @RequestMapping(value = "/companies/update", method = RequestMethod.POST)
    public String updateComp(Model model, @RequestParam int id, @RequestParam String name, @RequestParam String address) {

        DrugManufacturer drugManufacturer = drugManufacturerRepository.findById(id).get();
        drugManufacturer.setName(name);
        drugManufacturer.setAddress(address);
        drugManufacturerRepository.save(drugManufacturer);

        return "redirect:/companies/list";
    }

    @RequestMapping(value = "/companies/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        DrugManufacturer drugManufacturer = drugManufacturerRepository.findById(id).get();

        drugManufacturerRepository.delete(drugManufacturer);
        return "redirect:/companies/list";
    }


}
