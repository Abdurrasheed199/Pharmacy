package com.shifa.pharmacy.controller;

import com.shifa.pharmacy.model.Category;
import com.shifa.pharmacy.model.Drug;
import com.shifa.pharmacy.model.DrugManufacturer;
import com.shifa.pharmacy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    final
    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    public String categories(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "category/list";
    }


    @RequestMapping(value = "/categories/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "category/create";
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.POST)
    public String createCategory(@RequestParam String name, String parentId){

        if((parentId == null) || (parentId == "")){
            Category category = new Category();
            category.setName(name);
            category.setParent(null);
            categoryRepository.save(category);
            return "redirect:/categories/list";
        }else {
            Category parent = categoryRepository.findById(Integer.parseInt(parentId)).get();
            Category category = new Category();

            category.setName(name);
            category.setParent(parent);
            categoryRepository.save(category);

            return "redirect:/categories/list";
        }
    }

    @RequestMapping(value="/categories/super", method = RequestMethod.GET)
    public String superCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findCategoryByParent());
        return "redirect:/categories/list";
    }

    @RequestMapping(value="/categories/sub/{id}", method = RequestMethod.GET)
    public String subCategories(Model model, @PathVariable("id") int id) {
        model.addAttribute("categories", categoryRepository.findCategoriesByParentId(id));

        return "redirect:/categories/list";
    }

    @RequestMapping(value = "/categories/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("category", categoryRepository.findById(id).get());
        return "category/edit";
    }

    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    public String updateDrug(Model model, @RequestParam int id, @RequestParam String name, String parentId) {
        Category parent = categoryRepository.findById(Integer.parseInt(parentId)).get();
        Category category = new Category();
        category.setName(name);

        category.setParent(parent);

        categoryRepository.save(category);

        return "redirect:/categories/list";
    }

    @RequestMapping(value = "/categories/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Category category = categoryRepository.findById(id).get();

        categoryRepository.delete(category);
        return "redirect:/categories/list";

    }
}
