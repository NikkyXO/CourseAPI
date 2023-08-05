package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class swagController {
   
	@GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
	@RequestMapping(value = "/products", method = RequestMethod.GET)
   public List<String> getProducts() {
      List<String> productsList = new ArrayList<>();
      productsList.add("Honey");
      productsList.add("Almond");
      return productsList;
   }
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public String createProduct() {
      return "Product is saved successfully";
   }
}







