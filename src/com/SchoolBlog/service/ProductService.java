package com.SchoolBlog.service;

import com.SchoolBlog.model.Product;

public interface ProductService {
	Product add(Product product);
	Product get(long id);
	
}
