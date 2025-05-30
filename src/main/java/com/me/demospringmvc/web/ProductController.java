package com.me.demospringmvc.web;

import com.me.demospringmvc.entities.Product;
import com.me.demospringmvc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}
