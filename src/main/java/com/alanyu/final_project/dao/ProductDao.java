package com.alanyu.final_project.dao;

import com.alanyu.final_project.models.Product;
import com.alanyu.final_project.rowMapper.ProductRowMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {


	// HikariCP DataSource
	private static HikariDataSource dataSource;

	// Initialize the HikariCP DataSource
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(
				"jdbc:mysql://localhost:3306/mall?serverTimezone=America/Chicago&characterEncoding=utf-8");
		config.setUsername("root");
		config.setPassword("password");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");

		// Optional HikariCP configuration settings
		config.setMaximumPoolSize(10);
		config.setConnectionTimeout(30000);
		config.setIdleTimeout(600000);
		config.setMaxLifetime(1800000);

		dataSource = new HikariDataSource(config);
	}

	public Product getProductById(Integer productId) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_GET_PRODUCT_BY_ID").returningResultSet("product", new ProductRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_product_id", productId);
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Product> productList = (List<Product>) out.get("product");

		return productList.stream().findAny().orElse(null);
	}

	public List<Product> getAllProduct() {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_GET_ALL_PRODUCT").returningResultSet("o_product_list", new ProductRowMapper());

		SqlParameterSource in = new MapSqlParameterSource();
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Product> productList = (List<Product>) out.get("o_product_list");

		return productList;

	}

	public Integer insertProduct(Product product) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_INSERT_PRODUCT").returningResultSet("product", new ProductRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_product_name",
						product.getProductName()).addValue("i_category", product.getCategory())
				.addValue("i_image_url", product.getImageUrl()).addValue("i_price", product.getPrice())
				.addValue("i_stock", product.getStock())
				.addValue("i_description",
						product.getDescription());
		Map<String, Object> out = simpleJdbcCall.execute(in);
		Integer productId = (Integer) out.get("o_product_id");

		return productId;

	}

	public Product deleteProduct(Integer productId) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_DELETE_PRODUCT_BY_ID").returningResultSet("product", new ProductRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_product_id", productId);
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Product> productList = (List<Product>) out.get("product");
		if (null == productList) {
			return null;
		} else {
			return productList.get(0);
		}

	}

	public Product updateProductById(Product product) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_UPDATE_PRODUCT").returningResultSet("product", new ProductRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_product_id",
						product.getProductId()).addValue("i_product_name",
						product.getProductName()).addValue("i_category", product.getCategory())
				.addValue("i_image_url", product.getImageUrl()).addValue("i_price", product.getPrice())
				.addValue("i_stock", product.getStock())
				.addValue("i_description",
						product.getDescription());
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Product> productList = (List<Product>) out.get("product");

		return productList.stream().findAny().orElse(null);
	}
}
