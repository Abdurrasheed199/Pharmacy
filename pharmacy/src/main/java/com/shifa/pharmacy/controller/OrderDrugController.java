package com.shifa.pharmacy.controller;

import com.shifa.pharmacy.model.Order;
import com.shifa.pharmacy.model.OrderDrug;
import com.shifa.pharmacy.repository.OrderDrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderDrugController {

    final
    OrderDrugRepository orderDrugRepository;

    public OrderDrugController(OrderDrugRepository orderDrugRepository) {
        this.orderDrugRepository = orderDrugRepository;
    }

    @RequestMapping(value = "/orderDrugs/list", method = RequestMethod.GET)
    public String orderDrugs(Model model) {
        model.addAttribute("orderDrugs", orderDrugRepository.findAll());
        return "orderDrug/list";
    }

    @RequestMapping(value = "/orderDrug/{id}",method = RequestMethod.GET)
    public String orderDrugDetails(@PathVariable("id") int id){
        OrderDrug orderDrug = orderDrugRepository.findById(id).get();
        return "redirect:/orderDrugs/list";

    }

}
