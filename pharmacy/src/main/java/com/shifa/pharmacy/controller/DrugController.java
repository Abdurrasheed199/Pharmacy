package com.shifa.pharmacy.controller;

import com.shifa.pharmacy.model.Branch;
import com.shifa.pharmacy.model.Category;
import com.shifa.pharmacy.model.Drug;
import com.shifa.pharmacy.model.DrugManufacturer;
import com.shifa.pharmacy.repository.BranchRepository;
import com.shifa.pharmacy.repository.CategoryRepository;
import com.shifa.pharmacy.repository.DrugManufacturerRepository;
import com.shifa.pharmacy.repository.DrugRepository;
import com.shifa.pharmacy.service.DrugServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DrugController {

    final
    DrugServiceImpl drugService;

     final DrugRepository drugRepository;

     final DrugManufacturerRepository drugManufacturerRepository;

     final
     CategoryRepository categoryRepository;

     final
     BranchRepository branchRepository;

    public DrugController(DrugRepository drugRepository, DrugManufacturerRepository drugManufacturerRepository, DrugServiceImpl drugService, CategoryRepository categoryRepository, BranchRepository branchRepository) {
        this.drugRepository = drugRepository;
        this.drugManufacturerRepository = drugManufacturerRepository;
        this.drugService = drugService;
        this.categoryRepository = categoryRepository;
        this.branchRepository = branchRepository;
    }

    @RequestMapping(value = "/drugs/list", method = RequestMethod.GET)
    public String drugs(Model model) {
        model.addAttribute("drugs", drugRepository.findAll());
        return "drug/list";
    }

    @RequestMapping(path = "/drugs/category/{id}", method = RequestMethod.GET)
    public String subDrugs(@PathVariable("id") int id){

        List<Drug> drugs = drugService.drugs(id);
        return "redirect:/drugs/list";
    }

    @RequestMapping(path = "/drugs/detail/{id}", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable("id") int id){

        Drug d = drugRepository.findById(id).get();
        List<String> categories = new ArrayList<>();
        for(Category category: d.getCategories()){
            categories.add(category.getName());
        }
        model.addAttribute("drug", d);
        model.addAttribute("categories", categories);
        return "drug/details";
    }

    // Get the details of a product by its id.
    @RequestMapping(value = "/drug/{id}", method = RequestMethod.GET)
    public String drug(@PathVariable("id") int id){
        Drug drug = drugRepository.findById(id).get();

        return "redirect:/drugs/list";
    }


    @RequestMapping(value = "/drugs/create", method = RequestMethod.GET)
    public String createDrug(Model model) {
        model.addAttribute("drugManufacturers", drugManufacturerRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "drug/create";
    }


    @RequestMapping(value = "/drugs/add", method = RequestMethod.POST)
    public String create(Model model, @RequestParam String id, @RequestParam String name, @RequestParam double price, @RequestParam List<String> categories, @RequestParam("file") MultipartFile file, @RequestParam String description) {

        DrugManufacturer drugManufacturer = drugManufacturerRepository.findById(Integer.parseInt(id)).get();
        Drug drug = drugService.createDrug(drugManufacturer,name, price, categories, file, description);
        drugRepository.save(drug);



        return "redirect:/drugs/list";
    }

    @RequestMapping(value = "/drugs/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("drug", drugRepository.findById(id).get());
        return "drug/edit";
    }

    @RequestMapping(value = "/drugs/update", method = RequestMethod.POST)
    public String updateDrug(Model model, @RequestParam int id, @RequestParam DrugManufacturer drugManufacturer, @RequestParam String name, @RequestParam double price, /*@RequestParam("file") MultipartFile file,*/ @RequestParam String description) {

        Drug drug = drugRepository.findById(id).get();
        drug.setDrugManufacturer(drugManufacturer);
        drug.setName(name);
        drug.setPrice(price);
        drug.setDescription(description);

        drugRepository.save(drug);

        return "redirect:/drugs/list";
    }

    @RequestMapping(value = "/drugs/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Drug drug = drugRepository.findById(id).get();

        drugRepository.delete(drug);
        return "redirect:/drugs/list";

    }

   /* @RequestMapping(value = "/drugs/details/{id}", method = RequestMethod.GET)
    public String drugDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("drug", drugRepository.findById(id).get());

        return "drug/detail";

    }*/

}