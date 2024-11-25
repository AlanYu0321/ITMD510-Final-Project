package com.alanyu.final_project.models;

import com.alanyu.final_project.Enum.ProductCategory;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class Product {

	private Integer productId;
	private String productName;
	private ProductCategory category;
	private String imageUrl;
	private Integer price;
	private Integer stock;
	private String description;
	private Timestamp createDate;
	private Timestamp amendDate;

}
