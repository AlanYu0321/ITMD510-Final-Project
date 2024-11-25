package com.alanyu.final_project.service.impl;

import com.alanyu.final_project.dao.ProductDao;
import com.alanyu.final_project.models.Product;
import com.alanyu.final_project.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao = new ProductDao();

	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public List<Product> getAllProduct() {
		return productDao.getAllProduct();
	}

	@Override
	@Transactional
	public Product insertProduct(Product product) {

		Integer productId = productDao.insertProduct(product);
		Product returnProduct = new Product();
		returnProduct.setProductId(productId);

		return returnProduct;
	}

	@Override
	@Transactional
	public Product deleteProductById(Integer productId) {
		productDao.deleteProduct(productId);
		return productDao.getProductById(productId);
	}

	@Override
	@Transactional
	public Product updateProductById(Product product) {
		Product returnProduct = productDao.updateProductById(product);
		return returnProduct;
	}

}
