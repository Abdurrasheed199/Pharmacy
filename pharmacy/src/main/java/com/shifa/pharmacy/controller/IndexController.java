package com.shifa.pharmacy.controller;

import com.shifa.pharmacy.model.User;
import com.shifa.pharmacy.repository.DrugRepository;
import com.shifa.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    final
    UserRepository userRepository;

    final
    DrugRepository drugRepository;

    public IndexController(UserRepository userRepository, DrugRepository drugRepository) {
        this.userRepository = userRepository;
        this.drugRepository = drugRepository;
    }

    @GetMapping("/dashboards/admin")
    public String dashboard(Model model, Authentication authentication) {

        String username = authentication.getName();
        User u = userRepository.findUserByUsername(username);
        model.addAttribute("user", u );
        return "dashboard/dashboard";
    }


    @GetMapping("/layout")
    public  String layout(Model model){
        return "/layout";
    }

    @GetMapping("/")
    public  String index(Model model){
        model.addAttribute("drug", drugRepository.findAll());
        return "/index";
    }

    @GetMapping("/about us")
    public  String us(Model model){
        return "/about us";
    }

    @GetMapping("/contact us")
    public  String contact(Model model){
        return "/contact us";
    }





}
