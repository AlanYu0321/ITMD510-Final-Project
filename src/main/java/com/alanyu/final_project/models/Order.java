package com.alanyu.final_project.models;

import java.util.Date;
import lombok.Data;

@Data
public class Order {

	private Integer orderId;
	private String productName;
	private Integer productId;
	private Integer price;
	private Integer quantity;
	private Integer totalPrice;
	private String custName;
	private Integer custId;
	private Date createDate;
	private Date amendDate;

}
