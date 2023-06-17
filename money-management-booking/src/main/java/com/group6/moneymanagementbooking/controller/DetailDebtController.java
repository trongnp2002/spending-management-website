package com.group6.moneymanagementbooking.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.group6.moneymanagementbooking.enity.Debt_detail;
import com.group6.moneymanagementbooking.service.DetailDebtService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class DetailDebtController {
    private final DetailDebtService detailDebtService;


    @GetMapping("/view-detail-debt/{id}")
    public String listDetailDebt(Model model, @PathVariable("id") int id){
       model.addAttribute("list_detail", detailDebtService.findAllById(id));
       model.addAttribute("record", detailDebtService.findAllById(id).size());
       model.addAttribute("id", id);
        return "view-detail-debt";
    }


  


    @GetMapping("/add-detail-debt/{id}")
    public String registerGet(Model model, @PathVariable("id") int id){
        Debt_detail debt_detail =  new Debt_detail();
        debt_detail.setDeptorId(id);
        model.addAttribute("debt_detail", debt_detail);
         model.addAttribute("title", "Add New");
        return "add-detail-debt";
    }

    
    @PostMapping("/add-detail-debt")
    public RedirectView addDetailDebt(Model model, @ModelAttribute("debt_detail") Debt_detail detail_edbt) throws Exception{
       detail_edbt.setDate_create(LocalDateTime.now());
        detailDebtService.Save(detail_edbt);
         RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/view-detail-debt/" + detail_edbt.getDeptorId());
        return redirectView;
    }

    @GetMapping("/delete-detail-debt/{id}")
    public RedirectView  deleteDeatil(Model model, @PathVariable("id") int id){
         Debt_detail deb  = detailDebtService.findById(id);
       detailDebtService.deleteDebtById(id);
       RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/view-detail-debt/" + deb.getDeptorId());
        return redirectView;
    }

    @GetMapping("/edit-detail-debt/{id}")
    public String editDebt(Model model, @PathVariable("id") int id){
        Debt_detail deb  = detailDebtService.findById(id);
        model.addAttribute("debt_detail", deb);
         model.addAttribute("title", "Edit");
        return "add-detail-debt";
    }
}
