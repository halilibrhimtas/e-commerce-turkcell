package com.turkcell.spring.starter.controllers;

// CTRL + SPACE => Intelissense'i triggerlar

import com.turkcell.spring.starter.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("home")
@RequiredArgsConstructor

// http://localhost:8080/home
// http://localhost:8080/home/index
// http://localhost:8080/home/categories

// METHOD => GET,POST,PUT,DELETE...


// GET => Cevap olarak bir kaynak dönüleceği durumlarda kullanılır. Örn: ürünleri listeleme fonksiyonu
// POST => Bir kaynak oluşturulması isteği durumlarında kullanılır. Örn: ürün ekleme fonksiyonu
// PUT => Bir kaynağın değiştirilmesi isteği durumunda kullanılır. Örn: ürün güncellenmesi.
// DELETE => Bir kaynağın silinmesi isteği durumunda kullanılır. Örn: ürünün dbden silinmesi.
public class HomeController {
    MessageSource messageSource;

    // http://localhost:8080/home GET İSTEĞİ
    @GetMapping("")
    public String get(){
        return "Merhaba Turkcell";
    }

    // http://localhost:8080/home POST İSTEĞİ
    @PostMapping("")
    public String getPost(){
        return messageSource.getMessage("hello", null, LocaleContextHolder.getLocale());
    }

    //http://localhost:8080/home/index
    @GetMapping("index")
    public String get2(){
        return "Merhaba Turkcell 2";
    }

    @GetMapping("products")
    public List<Product> getProducts(){
        List<Product> productList = new ArrayList<>();


        // InMemory

        return productList;
    }

    // Query String =>  localhost:8080/home/getById?id=1&name=deneme
    // Route => localhost:8080/home/getById/1/deneme
    @GetMapping("getById")
    public Product getById(@RequestParam("id") int id){
        Product product = new Product();
        return product;
    }

    // Her bir temel entitynin kendi controllerinin bulunması best practicedir.
    // 8:00'de dersteyiz..

}
