package com.shifa.pharmacy.controller;

import com.shifa.pharmacy.model.Branch;
import com.shifa.pharmacy.model.Drug;
import com.shifa.pharmacy.repository.DrugRepository;
import com.shifa.pharmacy.repository.BranchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;
import java.util.UUID;

// hello world
@Controller
public class BranchController {

    final BranchRepository branchRepository;

    final DrugRepository drugRepository;


    public BranchController(BranchRepository branchRepository, DrugRepository drugRepository) {
        this.branchRepository = branchRepository;
        this.drugRepository = drugRepository;
    }

    @RequestMapping(value = "/branches/list", method = RequestMethod.GET)
    public String branches(Model model){
        model.addAttribute("branches", branchRepository.findAll());
        return "pharmacy/list";
    }

    @RequestMapping(value = "/branches/create", method = RequestMethod.GET)
    public String createBranch(Model model) {
        return "pharmacy/create";
    }

   /* public String generateUniqueNum() {
        Random r = new Random(); //instance of random class
        //generate random values from 0-100000
        int num = r.nextInt(100000);
        String uniqueNum = ("00" + Integer.toString(num));
        return uniqueNum;
    }*/

    @RequestMapping(value = "/branches/add", method = RequestMethod.POST)
    public String addBranch(Model model, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber /*@RequestParam String registrationNumber,*/ /*@RequestParam List<Drug> drug*/) {


       /* String registrationNumber = UUID.randomUUID().toString();
*/
        Branch branch = new Branch();
        branch.setName(name);
        branch.setAddress(address);
        branch.setPhoneNumber(phoneNumber);
       /* branch.setRegistrationNumber(registrationNumber);*/
        branchRepository.save(branch);
        return "redirect:/branches/list";
    }

    @RequestMapping(value = "/branches/edit/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable("id") int id, Model model) {

        model.addAttribute("branches", branchRepository.findById(id).get());
        return "pharmacy/edit";
    }


    @RequestMapping(value = "/branches/update", method = RequestMethod.POST)
    public String updateBranch(Model model, @RequestParam int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber/*, @RequestParam String registrationNumber, @RequestParam List<Drug> drug*/) {


        Branch branch = branchRepository.findById(id).get();
        branch.setName(name);
        branch.setAddress(address);
        branch.setPhoneNumber(phoneNumber);
       /* pharmacy.setRegistrationNumber(registrationNumber);
        pharmacy.setDrug(drug);
*/
        branchRepository.save(branch);

        return "redirect:/branches/list";
    }

    @RequestMapping(value = "/branches/delete/{id}", method = RequestMethod.GET)
    public String removeBranch(@PathVariable("id") int id, Model model) {

        Branch branch = branchRepository.findById(id).get();

        branchRepository.delete(branch);
        return "redirect:/branches/list";
    }

}
