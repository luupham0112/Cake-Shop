package com.phanmem.cakeshop.controller.user;


import com.phanmem.cakeshop.service.CategoryService;
import com.phanmem.cakeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping("home")
    public String home(){
        return "user/home";
    }
    @GetMapping("shop")
    public String getAllProduct(ModelMap model){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "user/home";
    }
    @GetMapping("shop/category/{id}")
    public String getAllProduct(ModelMap model, @PathVariable("id")Long id){
        model.addAttribute("products", productService.findProductByCategoryId(id));
        model.addAttribute("categories",categoryService.findAll());
        return "user/home";
    }


}
