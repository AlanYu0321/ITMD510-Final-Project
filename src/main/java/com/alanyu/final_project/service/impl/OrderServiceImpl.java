package com.alanyu.final_project.service.impl;

import com.alanyu.final_project.dao.OrderDao;
import com.alanyu.final_project.models.Order;
import com.alanyu.final_project.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	OrderDao orderDao = new OrderDao();

	@Override
	public Order getOrderById(Integer orderId) {
		return orderDao.getOrderById(orderId);
	}

	@Override
	public List<Order> getOrderByCustId(Integer custId) {
		return orderDao.getOrderByCustId(custId);
	}

	@Override
	public List<Order> getAllOrder() {
		return orderDao.getAllProduct();
	}

	@Override
	@Transactional
	public Order insertOrder(Order order) {
		return orderDao.getOrderById(orderDao.insertOrder(order));
	}

	@Override
	@Transactional
	public Order deleteOrderById(Integer orderId) {
		return orderDao.deleteProduct(orderId);
	}

	@Override
	public Order updateOrderById(Order order) {
		return orderDao.updateProductById(order);
	}
}
