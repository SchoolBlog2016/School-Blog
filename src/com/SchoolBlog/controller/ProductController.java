package com.SchoolBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SchoolBlog.model.Product;
import com.SchoolBlog.model.ProductFrom;
import com.SchoolBlog.service.ProductService;

@Controller
public class ProductController {
	private static final Log logger=LogFactory
			.getLog(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/product_input")
	public String inputProduct(){
		logger.info("inputProduct called");
		return "ProductFrom";
	}
	
	@RequestMapping( value="/product_save", method=RequestMethod.POST)
	public String saveProduct(HttpServletRequest request,
			RedirectAttributes redirectAttributes){
		logger.info("saveProduct called");
		ProductFrom productFrom=new ProductFrom();
		productFrom.setName(request.getParameter("name"));
		productFrom.setDescription(request.getParameter("description"));
		productFrom.setPrice(request.getParameter("price"));
		
		Product product=new Product();
		product.setName(productFrom.getName());
		product.setDescription(productFrom.getDescription());
		try{
			product.setPrice(Float.parseFloat(productFrom.getPrice()));
		}catch(NumberFormatException e){
			
		}
		System.out.println("Add A New Product: "+product.getName());
		Product savedProduct= productService.add(product);
		
		redirectAttributes.addFlashAttribute("message",
				"The product was successfully added.");
		return "redirect:/product_view/"+savedProduct.getId();
	}
	
	@RequestMapping(value="/product_view/{id}")
	public String viewProduct(@PathVariable Long id, Model modle){
		Product product=productService.get(id);
		System.out.println(product.getId());
		modle.addAttribute("product",product);
		
		return "ProductDetails";
	}
	
	

}
