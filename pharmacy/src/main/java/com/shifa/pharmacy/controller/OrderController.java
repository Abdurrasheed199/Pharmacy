package com.shifa.pharmacy.controller;

import com.shifa.pharmacy.model.*;
import com.shifa.pharmacy.repository.DrugRepository;
import com.shifa.pharmacy.repository.OrderDrugRepository;
import com.shifa.pharmacy.repository.OrderRepository;
import com.shifa.pharmacy.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    final OrderRepository orderRepository;
    final PatientRepository patientRepository;
    final OrderDrugRepository orderDrugRepository;
    final DrugRepository drugRepository;

    public OrderController(OrderRepository orderRepository, PatientRepository patientRepository, OrderDrugRepository orderDrugRepository, DrugRepository drugRepository) {
        this.orderRepository = orderRepository;
        this.patientRepository = patientRepository;
        this.orderDrugRepository = orderDrugRepository;
        this.drugRepository = drugRepository;
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "order/create";
    }

    @RequestMapping(value = "/orders/checkout", method = RequestMethod.POST)
    public String createOrder(Model model, @RequestParam int patientId, @RequestParam List<CheckoutOrder> checkoutOrders){
        Order order = new Order();
        long millis = System.currentTimeMillis();
        Date orderDate = new Date(millis);
        Patient patient = patientRepository.findById(patientId).get();
        order.setPatient(patient);
        order.setDate(orderDate);
        order.setStatus("Pending");
        orderRepository.save(order);

        List<OrderDrug> orderDrugs = null;

        Double totalPrice = 0.0D;
        for(CheckoutOrder co : checkoutOrders){
            int quantity = co.quantity;
            int drugId = co.drugId;
            Drug drug = drugRepository.findById(drugId).get();
            OrderDrug orderDrug = new OrderDrug(quantity,  order,drug, drug.getPrice());
            orderDrugRepository.save(orderDrug);
            orderDrugs.add(orderDrug);
            Double subTotal = drug.getPrice() * quantity;
            totalPrice += subTotal;
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);


        model.addAttribute("patient", patient);
        model.addAttribute("orderDrugs", orderDrugs);
        model.addAttribute("order", order);
        model.addAttribute("completed", "order successful");

        return "/order/completed";

    }

    @RequestMapping(value = "/orders/list", method = RequestMethod.GET)
    public String orders(Model model) {
        model.addAttribute("order", orderRepository.findAll());
        return "order/list";
    }

    @RequestMapping(value = "/orders/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("order", orderRepository.findById(id).get());
        return "order/edit";
    }

    @RequestMapping(value = "/orders/update", method = RequestMethod.POST)
    public String updateOrder(Model model, @RequestParam int id, @RequestParam Patient patient, @RequestParam Double totalPrice, @RequestParam String status) {

        Order order = orderRepository.findById(id).get();
        order.setPatient(patient);
        order.setTotalPrice(totalPrice);
        order.setStatus(status);

        orderRepository.save(order);

        return "redirect:/orders/list";
    }

    @RequestMapping(value = "/orders/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Order order = orderRepository.findById(id).get();

        orderRepository.delete(order);
        return "redirect:/orders/list";

    }

}
