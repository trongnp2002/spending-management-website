package com.group6.moneymanagementbooking.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group6.moneymanagementbooking.enity.Debtor;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.service.DebtorService;
import com.group6.moneymanagementbooking.service.UsersService;
import com.group6.moneymanagementbooking.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/Debtor")
public class DebtorController {
    private final DebtorService debtorService;
    private final UsersService usersService;

    @GetMapping("/ListAll")
    public String listDebtor(Model model) {
        model.addAttribute("list_debtor", debtorService.findAll(getIdUser()));
        model.addAttribute("record", debtorService.findAll(getIdUser()).size());
        return "view-debtor";
    }

    @GetMapping("/ListDebtor")
    public String AllDebtor(Model model) {
        List<Debtor> listdeb = new ArrayList<>();
        for (var deb : debtorService.findAll(getIdUser())) {
            if (deb.getTotal() > 0) {
                listdeb.add(deb);
            }
        }
        model.addAttribute("list_debtor", listdeb);
        model.addAttribute("record", listdeb.size());
        return "view-debtor";
    }

    @GetMapping("/ListOwner")
    public String AllOwner(Model model) {
        List<Debtor> listdeb = new ArrayList<>();
        for (var deb : debtorService.findAll(getIdUser())) {
            if (deb.getTotal() < 0) {
                listdeb.add(deb);
            }
        }
        model.addAttribute("list_debtor", listdeb);
        model.addAttribute("record", listdeb.size());
        return "view-debtor";
    }

    @GetMapping("/Add")
    public String addNew(Model model, HttpServletRequest request) {
        Debtor debtor = new Debtor();
        debtor.setUserId(getIdUser());
        model.addAttribute("isUpdate", false);
        model.addAttribute("debtor", debtor);
        model.addAttribute("title", "Add");
        return "add-debtor";
    }

    @PostMapping("/Add")
    public String addNew(@ModelAttribute("debtor") Debtor debtor) throws Exception {
        debtor.setTotal(0.0);
        debtor.setDate_create(LocalDateTime.now());
        debtor.setDate_update(LocalDateTime.now());
        debtorService.Save(debtor);
        return "redirect:/Debtor/ListAll";
    }

    @PostMapping("/Search")
    public String searchDebtor(Model model, @RequestParam("nameDebtor") String name) throws Exception {
        model.addAttribute("list_debtor", debtorService.SearchByName(name, getIdUser()));
        model.addAttribute("record", debtorService.SearchByName(name, getIdUser()).size());
        model.addAttribute("nameDebtor", name);
        return "view-debtor";
    }

    @GetMapping("/edit/{id}")
    public String registerGet(Model model, @PathVariable("id") int id) {
        Optional<Debtor> debtor = debtorService.getDebtor(id);
        model.addAttribute("isUpdate", true);
        model.addAttribute("debtor", debtor.get());
        model.addAttribute("title", "Update");
        return "add-debtor";
    }

    @PostMapping("/update")
    public String updateDebtor(Model model, @ModelAttribute("debtor") Debtor debtor) throws Exception {
        Optional<Debtor> debtors = debtorService.getDebtor(debtor.getId());
        debtor.setDate_create(debtors.get().getDate_create());
        debtor.setTotal(debtors.get().getTotal());
        debtor.setDate_update(LocalDateTime.now());
        debtorService.Save(debtor);
        return "redirect:/Debtor/ListAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteDebtor(Model model, @PathVariable("id") int id) {
        Debtor debtor = (debtorService.getDebtor(id)).get();
        if (debtor.getTotal() != 0) {
            model.addAttribute("list_debtor", debtorService.findAll(getIdUser()));
            model.addAttribute("record", debtorService.findAll(getIdUser()).size());
            model.addAttribute("mess", "Cannot be delete because the debt has not been completed!");
            return "view-debtor";
        }
        debtorService.deleteDebtorById(id);
        return "redirect:/Debtor/ListAll";
    }

    private int getIdUser() {
        Users users = usersService.getUserByEmail(SecurityUtils.getCurrentUsername());
        return users.getId();
    }

}
