package com.alanyu.final_project.service;

import com.alanyu.final_project.models.Order;
import java.util.List;

public interface OrderService {

	Order getOrderById(Integer orderId);
	List<Order> getOrderByCustId(Integer custId);
	List<Order> getAllOrder();
	Order insertOrder(Order order);
	Order deleteOrderById(Integer orderId);
	Order updateOrderById(Order order);

}
