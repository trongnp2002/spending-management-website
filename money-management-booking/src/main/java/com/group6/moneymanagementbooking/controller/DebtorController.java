package com.group6.moneymanagementbooking.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group6.moneymanagementbooking.enity.Debtor;
import com.group6.moneymanagementbooking.service.DebtorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class DebtorController {
    private final DebtorService debtorService;

    @GetMapping("/ListDebtor")
    public String listDebtor(Model model) {
        model.addAttribute("list_debtor", debtorService.findAll());
        model.addAttribute("record", debtorService.findAll().size());
        return "view-debtor";
    }

    @GetMapping("/AddDebtor")
    public String registerGet(Model model, HttpServletRequest request) {
        Debtor debtor = new Debtor();
        model.addAttribute("debtor", debtor);
         model.addAttribute("title", "Add");
        return "add-debtor";
    }

    @PostMapping("/Add-Debtor")
    public String registerPost(Model model, @ModelAttribute("debtor") Debtor debtor) throws Exception {
        debtor.setDate_create(LocalDateTime.now());
        debtor.setDate_update(LocalDateTime.now());
        debtorService.Save(debtor);
        return "redirect:/ListDebtor";
    }

    @PostMapping("/Search-Debtor")
    public String searchDebtor(Model model, @RequestParam("nameDebtor") String name) throws Exception {
        model.addAttribute("list_debtor", debtorService.SearchByName(name));
        model.addAttribute("record", debtorService.SearchByName(name).size());
        model.addAttribute("nameDebtor", name);
        return "view-debtor";
    }

    @GetMapping("/edit-Debtor/{id}")
    public String registerGet(Model model, @PathVariable("id") int id) {
        Optional<Debtor>  debtor = debtorService.getDebtor(id);
        model.addAttribute("debtor", debtor.get());
          model.addAttribute("title", "Update");
        return "add-debtor";
    }

     @GetMapping("/delete-Debtor/{id}")
    public String deleteDebtor(Model model, @PathVariable("id") int id) {
         debtorService.deleteDebtorById(id);
        return "redirect:/ListDebtor";
    }

   
}
