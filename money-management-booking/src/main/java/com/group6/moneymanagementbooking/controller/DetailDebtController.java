package com.group6.moneymanagementbooking.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.group6.moneymanagementbooking.enity.Debt_detail;
import com.group6.moneymanagementbooking.enity.Debtor;
import com.group6.moneymanagementbooking.service.DebtorService;
import com.group6.moneymanagementbooking.service.DetailDebtService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/Debtor/Detail")
public class DetailDebtController {
    private final DetailDebtService detailDebtService;
    private final DebtorService debtorService;

    @GetMapping("/view-detail/{id}")
    public String listDetailDebt(Model model, @PathVariable("id") int id) {
        model.addAttribute("list_detail", detailDebtService.findAllById(id));
        model.addAttribute("record", detailDebtService.findAllById(id).size());
        model.addAttribute("id", id);
        return "view-detail-debt";
    }

    @GetMapping("/Add/{id}")
    public String registerGet(Model model, @PathVariable("id") int id) {
        Debt_detail debt_detail = new Debt_detail();
        debt_detail.setDeptorId(id);
        model.addAttribute("debt_detail", debt_detail);
        model.addAttribute("title", "Add New");
        return "add-detail-debt";
    }

    @PostMapping("/Add")
    public RedirectView addDetailDebt(Model model, @ModelAttribute("debt_detail") Debt_detail detail_edbt,
            HttpServletRequest request)
            throws Exception {
        Debtor depDebtor = (debtorService.getDebtor(detail_edbt.getDeptorId())).get();
        depDebtor.setTotal(detail_edbt.isClassify() ? (depDebtor.getTotal() + detail_edbt.getMoney_debt())
                : (depDebtor.getTotal() - detail_edbt.getMoney_debt()));
        detailDebtService.Save(detail_edbt);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Debtor/Detail/view-detail/" + detail_edbt.getDeptorId());
        return redirectView;
    }

    @GetMapping("/Delete/{id}")
    public RedirectView deleteDeatil(Model model, @PathVariable("id") int id) {
        Debt_detail deb = detailDebtService.findById(id);
        detailDebtService.deleteDebtById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Debtor/Detail/view-detail/" + deb.getDeptorId());
        return redirectView;
    }

    @GetMapping("/Edit/{id}")
    public String editDebt(Model model, @PathVariable("id") int id) {
        Debt_detail deb = detailDebtService.findById(id);
        model.addAttribute("debt_detail", deb);
        model.addAttribute("title", "Edit");
        return "add-detail-debt";
    }
}
