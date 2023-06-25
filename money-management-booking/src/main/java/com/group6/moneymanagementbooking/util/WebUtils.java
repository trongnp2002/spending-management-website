package com.group6.moneymanagementbooking.util;

import java.util.List;

import org.springframework.ui.Model;

public class WebUtils {

    public static <T> String dispartcher(String url, Model model, List<T> list, String... params) {
        model.addAttribute("data", list);
        int i = 1;
        for (String param : params) {
            model.addAttribute("data" + i, param);
            i++;
        }
        return url;
    }

    public static <T> String dispartcher(String url, Model model, List<T> list, int curentpage, int maxPage) {
        model.addAttribute("data", list);
        model.addAttribute("data1", curentpage);
        model.addAttribute("data2", maxPage);

        return url;
    }

    public static <T> String dispartcher(String url, Model model, List<T> list, int curentpage, int maxPage,
            String... params) {
        model.addAttribute("data", list);
        model.addAttribute("data1", curentpage);
        model.addAttribute("data2", maxPage);
        int i = 3;
        for (String param : params) {
            model.addAttribute("data" + i, param);
            i++;
        }
        return url;
    }

    public static <T> String dispartcher(String url, Model model, List<T> list, int curentpage, int maxPage,
            Boolean... params) {
        model.addAttribute("data", list);
        model.addAttribute("data1", curentpage);
        model.addAttribute("data2", maxPage);
        int i = 3;
        for (Boolean param : params) {
            model.addAttribute("data" + i, param);
            i++;
        }
        return url;
    }

    public static <T> String adminDispartcher(String url, Model model, List<T> list, int curentpage,
            Boolean... params) {
        model.addAttribute("data", list);
        model.addAttribute("data1", curentpage);
        int i = 3;
        for (Boolean param : params) {
            model.addAttribute("data" + i, param);
            i++;
        }
        return url;
    }

    public static <T> String adminDispartcher(String url, Model model, List<T> list, int curentpage,
            boolean isLockedPage, boolean btnValue) {
        model.addAttribute("data", list);
        model.addAttribute("data1", curentpage);
        model.addAttribute("data3", !isLockedPage);
        model.addAttribute("data4", isLockedPage);
        if (isLockedPage) {
            model.addAttribute("data5", false);
            model.addAttribute("data6", btnValue);
        } else {
            model.addAttribute("data5", btnValue);
            model.addAttribute("data6", false);
        }
        return url;
    }

    public static <T> String dispartcher(String url, Model model, List<T> list, int... params) {
        model.addAttribute("data", list);
        int i = 1;
        for (int param : params) {
            model.addAttribute("data" + i, param);
            i++;
        }
        return url;
    }
}
