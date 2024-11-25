package com.alanyu.final_project.service;

import com.alanyu.final_project.models.Product;
import java.util.List;


public interface ProductService {

	Product getProductById(Integer productId);

	List<Product> getAllProduct();

	Product insertProduct(Product product);

	Product deleteProductById(Integer productId);

	Product updateProductById(Product product);

}
