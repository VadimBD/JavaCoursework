package org.example.onlinestore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinestore.entity.Product;
import org.example.onlinestore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.example.onlinestore.service.ProductService;
import org.example.onlinestore.utils.ISettings;
import org.example.onlinestore.viewmodels.LendingViewModel;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HomeController {

    private ProductService productService;
    private ISettings _settings;
    private  Utils utils;
    HttpServletRequest request;
    @Autowired
    public HomeController(ProductService productService, @Qualifier("settings")ISettings settings, Utils utils)
    {
        this.productService = productService;
        _settings = settings;
        this.utils = utils;
        this.request = request;
    }

    @GetMapping("/")
    public ModelAndView home(Model model) {
        var vm = new LendingViewModel();
        vm.topSales = productService.getTopSalesRnd( Integer.parseInt(_settings.getValue("TopSalesPerPage")));
        vm.noveltys = productService.getTopSalesRnd(Integer.parseInt(_settings.getValue("NoveltyPerPage")));


        ModelAndView mav = new ModelAndView("home");
        mav.addObject("viewModel", vm);

        return mav;
    }
}
