package com.me.demospringmvc.web;

import com.me.demospringmvc.entities.Product;
import com.me.demospringmvc.repository.ProductRepository;
import com.me.demospringmvc.repository.specification.ProductSpecification;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model,@RequestParam(required = false) String name) {
        Specification<Product> productsSpec = ProductSpecification.productNameLike(name);
        List<Product> products = productRepository.findAll(productsSpec);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public String home() {
        return "redirect:user/index";
    }

    @PostMapping("/admin/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String  deleteProducts(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/newProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "newProduct";
    }

    @PostMapping("/admin/saveProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "newProduct";
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        productRepository.save(newProduct);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSaveProduct(@Valid @ModelAttribute("product") Product product,
                                  BindingResult result,
                                  Model model) {

        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "editProduct";
        }

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            productRepository.save(existingProduct);
            return "redirect:/user/index";
        } else {
            model.addAttribute("errorMessage", "Product not found with ID: " + product.getId());
            return "errorPage";
        }
    }


    @GetMapping("/notAuth")
    public String notAuth() {
        return "notAuthenticated";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }



}
